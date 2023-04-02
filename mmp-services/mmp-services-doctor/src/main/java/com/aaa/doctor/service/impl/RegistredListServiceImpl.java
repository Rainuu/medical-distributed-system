package com.aaa.doctor.service.impl;

import com.aaa.core.entity.Registration;
import com.aaa.core.entity.Scheduling;
import com.aaa.core.vo.Result;
import com.aaa.doctor.dao.RegistredListDao;
import com.aaa.doctor.dao.SchedulingDao;
import com.aaa.doctor.service.RegistredListService;
import com.aaa.doctor.vo.RegisteredListVo;
import com.aaa.doctor.vo.SchedulingVoo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/27 11:35
 */
@Service
public class RegistredListServiceImpl implements RegistredListService {

    @Autowired
    private RegistredListDao registredListDao;

    @Autowired
    private SchedulingDao schedulingDao;

    @Override
    public Result<IPage<Registration>> getAllList(Integer currentPage, Integer pageSize,RegisteredListVo registeredListVo) {
        IPage<Registration> page = new Page<>(currentPage,pageSize);
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        // 部门id
        if (Objects.nonNull(registeredListVo.getDeptId())) {
            wrapper.eq("dept_id ",registeredListVo.getDeptId());
        }
        // 患者姓名
        if (StringUtils.hasText(registeredListVo.getPatientName())) {
            wrapper.like("patient_name",registeredListVo.getPatientName());
        }
        // 挂号类型
        if (Objects.nonNull(registeredListVo.getType())) {
            wrapper.eq("scheduling_type",registeredListVo.getType());
        }
        // 挂号时段
        if (Objects.nonNull(registeredListVo.getTimeInterval())) {
            wrapper.eq("subsection_type",registeredListVo.getTimeInterval());
        }
        // 挂号状态
        if (Objects.nonNull(registeredListVo.getStatus())) {
            wrapper.eq("registration_status",registeredListVo.getStatus());
        }
        // 部门id
        if (Objects.nonNull(registeredListVo.getTime())) {
            wrapper.eq("create_time",registeredListVo.getTime());
        }
        IPage<Registration> page1 = registredListDao.selectPage(page,wrapper);
        return new Result<IPage<Registration>>(2000,"查询患者信息成功",page1);
    }


    /*
    * 修改挂号列表状态  收费、退号、作废
    * */
    @Override
    public Result<Integer> handleSuccess(String registrationId) {
        Integer integerResult = registredListDao.handleSuccess(registrationId);
        return new Result<Integer>(2000,"收费成功",integerResult);
    }

    @Override
    public Result<Integer> handleReturn(String registrationId) {
        Integer integerResult = registredListDao.handleReturn(registrationId);
        return new Result<Integer>(2000,"退号成功",integerResult);
    }

    @Override
    public Result<Integer> handleFalse(String registrationId) {
        Integer integerResult = registredListDao.handleFalse(registrationId);
        return new Result<Integer>(2000,"作废成功",integerResult);
    }


    /**
     * 查询医生排班表
     * @param
     * @return
     */
    @Override
    public Result<IPage<Scheduling>> findDoctocList(Integer currentPage, Integer pageSize,SchedulingVoo schedulingVoo) {
        IPage<Scheduling> page = new Page<>(currentPage,pageSize);
        QueryWrapper<Scheduling> wrapper = new QueryWrapper<>();
        // 部门编号
        if (Objects.nonNull(schedulingVoo.getDeptId())) {
            wrapper.eq("dept_id",schedulingVoo.getDeptId());
        }
        // 挂号类型
        if (Objects.nonNull(schedulingVoo.getSchedulingType())) {
            wrapper.eq("scheduling_type",schedulingVoo.getSchedulingType());
        }
        // 挂号时段
        if (StringUtils.hasText(schedulingVoo.getSubsectionType())) {
            wrapper.eq("subsection_type",schedulingVoo.getSubsectionType());
        }
        // 挂号时间（值班日期）
        if (Objects.nonNull(schedulingVoo.getSchedulingDay())) {
            wrapper.eq("scheduling_day",schedulingVoo.getSchedulingDay());
        }
//        List<Scheduling> schedulings = schedulingDao.selectList(wrapper);
        IPage<Scheduling> page1 = schedulingDao.selectPage(page, wrapper);
        return new Result<>(2000,"查询医生排班成功",page1);
    }

    @Override
    public Boolean updRegistrationId(String registrationId, String status) {
        int i=-1;
        if (Objects.nonNull(status)){

            i = registredListDao.updStatus(registrationId,status);
        }
        return i>0?true:false;
    }


}
