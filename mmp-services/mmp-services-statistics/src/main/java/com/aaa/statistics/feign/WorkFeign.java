package com.aaa.statistics.feign;

import com.aaa.core.entity.Registration;
import com.aaa.core.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mmp-services-doctor") //获取system的nacos服务名，注册feign服务
public interface WorkFeign {
    @PostMapping("doctor/api/registration/queryByDoctor")
    Result<List<Registration>> queryByDoctor(@RequestParam String doctorName, @RequestParam String dateRange1, @RequestParam String dateRange2);

    @PostMapping("doctor/api/registration/queryByDoctor2")
    Result<List<Registration>> queryByDoctor2(@RequestParam String doctorName, @RequestParam String dateRange1, @RequestParam String dateRange2);


}
