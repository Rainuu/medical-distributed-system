package com.aaa.doctor.service;

import com.aaa.core.entity.Registration;
import com.aaa.core.vo.Result;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/31 20:28
 */
public interface ApiRegistrationService {

    Result<List<Registration>> queryByDoctor(String doctorName, String dateRange1, String dateRange2);

    Result<List<Registration>> ApiRegistrationSql(String doctorName, String dateRange1, String dateRange2);
}
