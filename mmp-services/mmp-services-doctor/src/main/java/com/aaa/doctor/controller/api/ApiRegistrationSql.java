package com.aaa.doctor.controller.api;

import com.aaa.core.entity.Registration;
import com.aaa.doctor.service.ApiRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/31 21:09
 */
@RestController
@RequestMapping("doctor/api")
public class ApiRegistrationSql {

    @Autowired
    private ApiRegistrationService apiRegistrationService;


}
