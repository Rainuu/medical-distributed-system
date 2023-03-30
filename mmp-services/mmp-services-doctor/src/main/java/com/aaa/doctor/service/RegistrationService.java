package com.aaa.doctor.service;

import com.aaa.core.entity.Registration;
import com.aaa.core.vo.Result;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/30 22:39
 */
public interface RegistrationService {


    /*
    * 查询新开就诊中的就诊列表状态
    * */
    List<Registration> queryRegistrationStatus1(String status);
}


