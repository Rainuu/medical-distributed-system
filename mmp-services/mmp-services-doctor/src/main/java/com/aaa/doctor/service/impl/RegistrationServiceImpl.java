package com.aaa.doctor.service.impl;

import com.aaa.core.entity.Registration;
import com.aaa.doctor.dao.RegistrationDao;
import com.aaa.doctor.service.RegistrationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/30 22:41
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationDao registrationDao;


    /*
     * 查询新开就诊中的就诊列表状态
     * */
    @Override
    public List<Registration> queryRegistrationStatus1(String status) {
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(status)) {
            wrapper.eq("registration_status",status);
        }
        List<Registration> registrations = registrationDao.selectList(wrapper);
        return registrations;
    }
}
