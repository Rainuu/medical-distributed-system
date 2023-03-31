package com.aaa.doctor.service;

import com.aaa.core.entity.Registration;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/31 20:28
 */
public interface ApiRegistrationService {

    List<Registration> queryByDoctor(Registration registration);



    List<Registration> ApiRegistrationSql(String userId);
}
