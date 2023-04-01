package com.aaa.doctor.controller.api;

import com.aaa.core.entity.Registration;
import com.aaa.core.vo.RegistrationVo;
import com.aaa.core.vo.Result;
import com.aaa.doctor.service.ApiRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param
     * @return
     */
    @PostMapping("queryByDoctor")
    public Result<List<Registration>> queryByDoctor(RegistrationVo registrationVo) {
        return apiregistrationService.queryByDoctor(registrationVo);
    }

    @PostMapping("queryByDoctor2")
    public Result<List<Registration>> queryByDoctor2(RegistrationVo registrationVo) {
        return apiregistrationService.ApiRegistrationSql(registrationVo);
    }

}
