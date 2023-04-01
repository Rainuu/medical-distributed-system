package com.aaa.statistics.feign;

import com.aaa.core.entity.Registration;
import com.aaa.core.vo.RegistrationVo;
import com.aaa.core.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "mmp-services-doctor") //获取system的nacos服务名，注册feign服务
public interface WorkFeign {
    @PostMapping("doctor/api/registration/queryByDoctor")
    public Result<List<Registration>> queryByDoctor(RegistrationVo registrationVo);
    @PostMapping("doctor/api/registration/queryByDoctor2")
    public Result<List<Registration>> queryByDoctor2(RegistrationVo registrationVo);


}
