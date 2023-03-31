package com.aaa.doctor.service.impl;

import com.aaa.core.entity.CareHistory;
import com.aaa.core.entity.User;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.doctor.dao.CareHistoryDao;
import com.aaa.doctor.feign.UserFeign;
import com.aaa.doctor.service.CareHistoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

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
    public Result<String> SaveHistroy(CareHistory careHistory) {
//        String token = WebUtil.getRequest().getHeader("token");
//        Map<String, Object> tokenData = JwtUtil.getTokenChaim(token);
//        String phone = (String) tokenData.get("username");
//        Long userId = userFeign.getByUsername(phone);//获取当前登录的人的信息
//        careHistory.setUserId(userId);

        return null;
    }


}
