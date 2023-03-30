package com.aaa.doctor.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.aaa.core.entity.Scheduling;
import com.aaa.core.entity.User;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.doctor.dao.SchedulingDao;
import com.aaa.doctor.feign.UserFeign;
import com.aaa.doctor.service.SchedulingService;
import com.aaa.doctor.vo.SchedulingDto;
import com.aaa.doctor.vo.SchedulingVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/29 9:55
 */
@Service
public class SchedulingServiceImpl implements SchedulingService {

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private SchedulingDao schedulingDao;

    @Override
    public Result queryScheduling(SchedulingVo schedulingVo) {
        //1.查询所有医生的信息
        Map<String, Object> map = new HashMap<>();
        map.put("userId", schedulingVo.getUserId());
        map.put("deptId", schedulingVo.getDeptId());

        List<User> users = userFeign.queryUsersNeedScheduling(map);

        return querySchedulingByCondition(schedulingVo, users);
    }



    @Override
    public List<User> queryUserName() {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userFeign.queryUsersNeedScheduling(map);
        return users;
    }



    //封装前端需要的数据
    private Result querySchedulingByCondition(SchedulingVo schedulingVo, List<User> users) {
        //获取当前时间
        DateTime date = DateUtil.date();
        if (StringUtils.hasText(schedulingVo.getQueryDate())) {
            //把字符串转化为时间类型
            date = DateUtil.parse(schedulingVo.getQueryDate(), "yyyy-MM-dd");
            int i = DateUtil.dayOfWeek(date); //返回的如果是2标识周一  1表示周日
            if (i == 1) {
                date = DateUtil.offsetDay(date, 1);
            } else {
                date = DateUtil.offsetDay(date, -1);
            }
        }

        //开始时间和结束时间
        DateTime beginDate = DateUtil.beginOfWeek(date);
        DateTime endDate = DateUtil.endOfWeek(date);
        schedulingVo.setBeginDate(DateUtil.format(beginDate, "yyyy-MM-dd"));
        schedulingVo.setEndDate(DateUtil.format(endDate, "yyyy-MM-dd"));
        //根据条件查询排班表
        List<Scheduling> schedulings = queryByCondition(schedulingVo);

        List<SchedulingDto> schedulingDtos=new ArrayList<>();

        for(User user :users){
            SchedulingDto schedulingDto1=new SchedulingDto(user.getUserId(),user.getDeptId(),"1",initMap(beginDate));
            SchedulingDto schedulingDto2=new SchedulingDto(user.getUserId(),user.getDeptId(),"2",initMap(beginDate));
            SchedulingDto schedulingDto3=new SchedulingDto(user.getUserId(),user.getDeptId(),"3",initMap(beginDate));
            //一个用户都有这三条数据
            schedulingDtos.add(schedulingDto1);
            schedulingDtos.add(schedulingDto2);
            schedulingDtos.add(schedulingDto3);



            for(Scheduling scheduling:schedulings){
                Long userId = scheduling.getUserId();//排班人的id
                String schedulingDay = scheduling.getSchedulingDay();//排班的日期
                String subsectionType = scheduling.getSubsectionType();
                if(user.getUserId().equals(userId)) {
                    switch (subsectionType) {
                        case "1":
                            Map<String, String> record1 = schedulingDto1.getRecord();
                            record1.put(schedulingDay, scheduling.getSchedulingType());
                            break;
                        case "2":
                            Map<String, String> record2 = schedulingDto2.getRecord();
                            record2.put(schedulingDay, scheduling.getSchedulingType());
                            break;
                        case "3":
                            Map<String, String> record3 = schedulingDto3.getRecord();
                            record3.put(schedulingDay, scheduling.getSchedulingType());
                            break;
                    }
                }
            }
            schedulingDto1.setSchedulingType(schedulingDto1.getRecord().values());
            schedulingDto2.setSchedulingType(schedulingDto2.getRecord().values());
            schedulingDto3.setSchedulingType(schedulingDto3.getRecord().values());
        }
        //封装表数据
        Map<String,Object> result=new HashMap<>();
        result.put("tableDate",schedulingDtos);
        result.put("beginDate",DateUtil.format(beginDate,"yyyy-MM-dd"));
        result.put("endDate",DateUtil.format(endDate,"yyyy-MM-dd"));
        //封装表头
        Object label = initLabel(beginDate);
        result.put("labelNames",label);
        return new Result(2000,"查询成功",result);
    }

    //封装标题
    private Object initLabel(DateTime beginTime) {
        String[] labelNames = new String[7];
        for (int i = 0; i < 7; i++) {
            DateTime d = DateUtil.offsetDay(beginTime, i);
            labelNames[i] = DateUtil.format(d,"yyyy-MM-dd") + formatterWeek(i);
        }
        return labelNames;
    }
    /**
     * 翻译周
     *
     * @param i
     * @return
     */
    private String formatterWeek(int i) {
        switch (i) {
            case 0:
                return "(周一)";
            case 1:
                return "(周二)";
            case 2:
                return "(周三)";
            case 3:
                return "(周四)";
            case 4:
                return "(周五)";
            case 5:
                return "(周六)";
            default:
                return "(周日)";
        }
    }
    //初始化当前用户这一周的排班信息
    private static Map<String, String> initMap(DateTime beginDate) {
        Map<String,String> map=new TreeMap<>();
        for(int i=0;i<7;i++){
            DateTime dateTime = DateUtil.offsetDay(beginDate, i);
            String key = DateUtil.format(dateTime, "yyyy-MM-dd");
            map.put(key,"");
        }
        return map;
    }



    //根据条件查询排班信息
    public List<Scheduling> queryByCondition(SchedulingVo schedulingVo) {
        QueryWrapper<Scheduling> wrapper = new QueryWrapper<>();
        if (schedulingVo.getUserId() != null) {
            wrapper.eq("user_id", schedulingVo.getUserId());
        }
        if(schedulingVo.getDeptId()!=null){
            wrapper.eq("dept_id",schedulingVo.getDeptId());
        }
        if(StringUtils.hasText(schedulingVo.getBeginDate())){
            wrapper.ge("scheduling_day",schedulingVo.getBeginDate());
        }
        if(StringUtils.hasText(schedulingVo.getEndDate())){
            wrapper.le("scheduling_day",schedulingVo.getEndDate());
        }
        return schedulingDao.selectList(wrapper);

    }



    /**
     * 我的排班
     * @param schedulingVo
     * @return
     */
    @Override
    public Result MyqueryScheduling(SchedulingVo schedulingVo) {
        String token = WebUtil.getRequest().getHeader("token");
        Map<String, Object> info = JwtUtil.getTokenChaim(token);
        String phone= (String) info.get("username");
        User byUsername = userFeign.getByUsername(phone);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", byUsername.getUserId());
        List<User> users = userFeign.queryUsersNeedScheduling(map);
        return querySchedulingByCondition(schedulingVo, users);
    }

    /**
     * 更改医生排班
     */
    @Override
    public Boolean upScheduling(String beginDate, List<SchedulingDto> data) {
        String token = WebUtil.getRequest().getHeader("token");
        Map<String, Object> tokenData = JwtUtil.getTokenChaim(token);
        String phone = (String) tokenData.get("username");
        User user = userFeign.getByUsername(phone);//获取当前登录的人的信息

        int del=1;
        int update=1;
        DateTime dateTime = DateUtil.parse(beginDate, "yyyy-MM-dd");
        Map<String, String> map = initMap(dateTime);
        Set<String> keys = map.keySet();

        for (SchedulingDto schedulingDto : data) {
            Collection<String> schedulingType = schedulingDto.getSchedulingType();//获取排班
            Iterator<String> scheduling = schedulingType.iterator();//排班 1门诊  2急诊
            Iterator<String> key = keys.iterator();//获取当前的日期
            for (int i = 0; i < keys.size(); i++) {
                String next = scheduling.next();
                String next1 = key.next();
                if (next.equals("")){
                    QueryWrapper<Scheduling> wrapper2 = new QueryWrapper<>();
                    if (Objects.nonNull(schedulingDto.getUserId())){
                        //查询用户id 当前的医生
                        wrapper2.eq("user_id",schedulingDto.getUserId());
                    }
                    if (Objects.nonNull(schedulingDto.getDeptId())){
                        //查询部门id
                        wrapper2.eq("dept_id",schedulingDto.getDeptId());
                    }
                    if (StringUtils.hasText(beginDate)){
                        //查询当前时间
                        wrapper2.eq("scheduling_day",next1);
                    }
                    if (Objects.nonNull(schedulingDto.getSubsectionType())){
                        //查询时段
                        wrapper2.eq("subsection_type",schedulingDto.getSubsectionType());
                    }
                    del = schedulingDao.delete(wrapper2);
                }else {
                    QueryWrapper<Scheduling> wrapper = new QueryWrapper<>();
                    if (Objects.nonNull(schedulingDto.getUserId())){
                        //查询用户id 当前的医生
                        wrapper.eq("user_id",schedulingDto.getUserId());
                    }
                    if (Objects.nonNull(schedulingDto.getDeptId())){
                        //查询部门id
                        wrapper.eq("dept_id",schedulingDto.getDeptId());
                    }
                    if (StringUtils.hasText(beginDate)){
                        //查询当前时间
                        wrapper.eq("scheduling_day",next1);
                    }
                    if (Objects.nonNull(schedulingDto.getSubsectionType())){
                        //查询时段
                        wrapper.eq("subsection_type",schedulingDto.getSubsectionType());
                    }
                    Long count = schedulingDao.selectCount(wrapper);
                    if (count>0?true:false){
                        UpdateWrapper<Scheduling> wrapper1 = new UpdateWrapper<>();
                        if (Objects.nonNull(schedulingDto.getUserId())){
                            //查询用户id 当前的医生
                            wrapper1.eq("user_id",schedulingDto.getUserId());
                        }
                        if (Objects.nonNull(schedulingDto.getDeptId())){
                            //查询部门id
                            wrapper1.eq("dept_id",schedulingDto.getDeptId());
                        }
                        if (StringUtils.hasText(beginDate)){
                            //查询当前时间
                            wrapper1.eq("scheduling_day",next1);
                        }
                        if (Objects.nonNull(schedulingDto.getSubsectionType())){
                            //查询时段
                            wrapper1.eq("subsection_type",schedulingDto.getSubsectionType());
                        }
                        if (!next.equals("")){
                            //修改""
                            wrapper1.set("scheduling_type",next);
                            DateTime date = DateUtil.date();
                            Scheduling scheduling1 = new Scheduling();
                            scheduling1.setUserId(schedulingDto.getUserId());//用户的id 相当于医生的id
                            scheduling1.setDeptId(schedulingDto.getDeptId());//部门id  相当于科室id
                            scheduling1.setSchedulingDay(next1); //值班的日期
                            scheduling1.setSubsectionType(schedulingDto.getSubsectionType());//值班的时段
                            scheduling1.setSchedulingType(next);//值班的类型 门诊 还是 急诊
                            scheduling1.setCreateTime(date);
                            scheduling1.setCreateBy(user.getUserName());
                            update = schedulingDao.update(scheduling1, wrapper1);

                        }
                    }else {
                        DateTime date = DateUtil.date();//获取当前时间
                        Scheduling scheduling1 = new Scheduling();
                        scheduling1.setUserId(schedulingDto.getUserId());//用户的id 相当于医生的id
                        scheduling1.setDeptId(schedulingDto.getDeptId());//部门id  相当于科室id
                        scheduling1.setSchedulingDay(next1); //值班的日期
                        scheduling1.setSubsectionType(schedulingDto.getSubsectionType());//值班的时段
                        scheduling1.setSchedulingType(next);//值班的类型 门诊 还是 急诊
                        scheduling1.setCreateTime(date);
                        scheduling1.setCreateBy(user.getUserName());
                        update = schedulingDao.insert(scheduling1);

                    }

                }
            }

        }
        Boolean aboolen=true;
        Boolean delete=del>0?true:false;
        aboolen=update>0?true:false;
        return aboolen;
    }

}
