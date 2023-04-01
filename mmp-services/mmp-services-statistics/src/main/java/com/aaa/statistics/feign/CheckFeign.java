package com.aaa.statistics.feign;

import com.aaa.core.entity.CheckResult;
import com.aaa.core.vo.CheckResultVo;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "mmp-services-check") //获取system的nacos服务名，注册feign服务
public interface CheckFeign {

    @PostMapping("check/checkResult/selectAll")
    public Result<List<CheckResult>> selectAll(CheckResultVo checkResultVo);

    @PostMapping("check/checkResult/selectAllTwo")
    public Result<List<CheckResult>> selectAllTwo(CheckResultVo checkResultVo);
}
