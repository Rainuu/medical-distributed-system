package com.aaa.doctor.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.aaa.core.entity.*;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.doctor.dao.CareHistoryDao;
import com.aaa.doctor.dao.CareOrderDao;
import com.aaa.doctor.dao.CareOrderItemDao;
import com.aaa.doctor.feign.UserFeign;
import com.aaa.doctor.service.CareHistoryService;
import com.aaa.doctor.util.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/25 10:20
 */
@Service
public class CareHistoryServiceImpl implements CareHistoryService {

    @Autowired
    private CareHistoryDao careHistoryDao;

    @Autowired
    private CareOrderItemDao careOrderItemDao;

    @Autowired
    private CareOrderDao careOrderDao;

    @Autowired
    private UserFeign userFeign;

    @Override
    public Result<List<CareHistory>> queryCareHistoryByPatientId(String patientId) {
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(patientId)) {
            wrapper.eq("patient_id",patientId);
        }
      List<CareHistory> historyList =  careHistoryDao.selectAll(patientId);

        return new Result(2000,"查询患者病历成功",historyList);
    }

    @Override
    public List<CareHistory> getCareHistoryId(String patientId) {
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(patientId)) {
            wrapper.eq("patient_id",patientId);
        }
        List<CareHistory> careHistoryList = careHistoryDao.selectList(wrapper);
        return careHistoryList;
    }




    /**
     * 保存病历
     */
    @Override
    public Result<CareHistory> SaveHistroy(String registrationId, CareHistory careHistory) {
        HttpServletRequest request = WebUtil.getRequest();
        String phone = (String) JwtUtil.getTokenChaim(request.getHeader("token")).get("username");
        User byUsername = userFeign.getByUsername(phone);//获取当前登录的人的信息
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        wrapper.eq("registrationId",registrationId);
        IdWorker worker = new IdWorker();
        long chId = worker.nextId();
        String str = "CH"+chId;
        careHistory.setChId(str);
        careHistory.setUserId(byUsername.getUserId());
        careHistory.setUserName(byUsername.getUserName());
        careHistoryDao.insert(careHistory);
        CareHistory careHistory1 = careHistoryDao.selectById(careHistory);
        return new Result<CareHistory>(2000,"添加成功",careHistory1);
    }

    @Override
    public String insertCareHistory(CareHistory careHistory) {
        String id=null;
        if (Objects.nonNull(careHistory.getChId())){
            String token = WebUtil.getRequest().getHeader("token");
            Map<String, Object> tokenData = JwtUtil.getTokenChaim(token);
            String phone = (String) tokenData.get("username");
            User user = userFeign.getByUsername(phone);//获取当前登录的人的信息
            Dept deptByDeptId = userFeign.getDeptByDeptId(user.getDeptId());


            DateTime date = DateUtil.date();
            String date1 = DateUtil.format(date, "yyyy-MM-dd");
            careHistory.setUserId(user.getUserId());
            careHistory.setUserName(user.getUserName());
            careHistory.setDeptId(user.getDeptId());
            careHistory.setDeptName(deptByDeptId.getDeptName());
            careHistory.setCareTime(new Date());
            careHistory.setCaseDate(date1);
            int insert = careHistoryDao.insert(careHistory);
        }else {
            IdWorker idWorker = new IdWorker();
            id = "CH" + idWorker.nextId();
        }

        return id;
    }

    @Override
    public CareHistory queryCareHistoryId(String patientId) {
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(patientId)){
            wrapper.eq("patient_id",patientId);
            wrapper.orderByDesc("care_time");
            List<CareHistory> careHistories = careHistoryDao.selectList(wrapper);
            CareHistory careHistory = careHistories.get(0);
            QueryWrapper<CareOrder> wrapper1 = new QueryWrapper<>();
            if (StringUtils.hasText(careHistory.getChId())){
                wrapper1.eq("ch_id",careHistory.getChId());
                List<CareOrder> careOrders = careOrderDao.selectList(wrapper1);
                careHistory.setCareOrderList(careOrders);
                for (CareOrder careOrder : careOrders) {
                    QueryWrapper<CareOrderItem> careOrderItemQueryWrapper = new QueryWrapper<>();
                    if (StringUtils.hasText(careOrder.getCoId())){
                        careOrderItemQueryWrapper.eq("co_id",careOrder.getCoId());
                        List<CareOrderItem> careOrderItems = careOrderItemDao.selectList(careOrderItemQueryWrapper);
                        careOrder.setCareOrderItemList(careOrderItems);
                    }
                }
            }
            return careHistory;

        }else {
            return null;
        }

    }


}
