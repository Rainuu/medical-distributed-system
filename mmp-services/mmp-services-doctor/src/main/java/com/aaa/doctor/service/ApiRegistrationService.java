package com.aaa.doctor.service;

import com.aaa.core.entity.Registration;
import com.aaa.core.vo.RegistrationVo;
import com.aaa.core.vo.Result;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/31 20:28
 */
public interface ApiRegistrationService {

    Result<List<Registration>> queryByDoctor(RegistrationVo registrationVo);
    Result<List<Registration>> ApiRegistrationSql(RegistrationVo registrationVo);
}
