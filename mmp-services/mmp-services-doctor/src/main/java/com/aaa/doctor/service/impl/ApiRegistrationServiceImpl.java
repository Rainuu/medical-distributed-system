package com.aaa.doctor.service.impl;

import com.aaa.core.entity.Registration;
import com.aaa.doctor.dao.ApiRegistrationDao;
import com.aaa.doctor.service.ApiRegistrationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/31 20:33
 */
@Service
public class ApiRegistrationServiceImpl implements ApiRegistrationService {
    @Autowired
    private ApiRegistrationDao apiRegistrationDao;

    @Override
    public List<Registration> queryByDoctor(Registration registration) {
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(registration.getDoctorName())) {
            wrapper.like("doctor_name",registration.getDoctorName());
        }
        if (Objects.nonNull(registration.getCreateTime())) {
            wrapper.eq("create_time",registration.getCreateTime());
        }
        List<Registration> registrations = apiRegistrationDao.selectList(wrapper);
        return registrations;
    }


    /**
     * 查询每个医生的挂号总金额和每个医生的接诊数量
     * @param userId
     * @return
     */
    @Override
    public List<Registration> ApiRegistrationSql(String userId) {
        List<Registration> registrations = apiRegistrationDao.selectSum(userId);
        return registrations;
    }
}
