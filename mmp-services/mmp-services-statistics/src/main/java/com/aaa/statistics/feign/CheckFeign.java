package com.aaa.statistics.feign;

import com.aaa.core.entity.CheckResult;
import com.aaa.core.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mmp-services-check") //获取system的nacos服务名，注册feign服务
public interface CheckFeign {

    @PostMapping("check/checkResult/selectAll")
    Result<List<CheckResult>> selectAll(@RequestParam String checkItemId, @RequestParam String patientName, @RequestParam String dateRange1,
                                        @RequestParam String dateRange2);

    @PostMapping("check/checkResult/selectAllTwo")
    Result<List<CheckResult>> selectAllTwo(@RequestParam String checkItemId, @RequestParam String dateRange1, @RequestParam String dateRange2);
}
