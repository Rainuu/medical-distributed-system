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
 * @date 2023/3/31 20:19
 */
@RestController
@RequestMapping("doctor/api/registration")
public class ApiRegistrationController {

    @Autowired
    private ApiRegistrationService apiregistrationService;

    /**
     * 模糊查询信息
     * @param registration
     * @return
     */
    @GetMapping("queryByDoctor")
    public List<Registration> queryByDoctor(Registration registration) {
        return apiregistrationService.queryByDoctor(registration);
    }

    @GetMapping("ApiRegistrationSql")
    public List<Registration> ApiRegistrationSql(@PathVariable String userId) {
        return apiregistrationService.ApiRegistrationSql(userId);
    }

}
