package com.aaa.doctor.controller;

import com.aaa.core.entity.User;
import com.aaa.core.vo.Result;
import com.aaa.doctor.service.SchedulingService;
import com.aaa.doctor.vo.SchedulingDto;
import com.aaa.doctor.vo.SchedulingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/29 9:41
 */
@RestController
@RequestMapping("doctor/scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    @PostMapping("queryScheduling")
    public Result queryScheduling(@RequestBody SchedulingVo schedulingVo) {

        System.out.println(schedulingVo.toString());

        return schedulingService.queryScheduling(schedulingVo);
    }

    /**
     * 我的排班
     *
     * @param schedulingVo
     * @return
     */
    @PostMapping("MyqueryScheduling")
    public Result MyqueryScheduling(@RequestBody SchedulingVo schedulingVo) {

        System.out.println(schedulingVo.toString());

        return schedulingService.MyqueryScheduling(schedulingVo);
    }

    @PostMapping("queryUserName")
    public Result queryUserName() {
        List<User> users = schedulingService.queryUserName();
        return new Result(2000, "查询所有的医生成功", users);
    }


    /**
     * 更改医生排班
     */
    @PostMapping("upScheduling/{beginDate}")
    public Result upScheduling(@PathVariable String beginDate, @RequestBody List<SchedulingDto> data) {
        Boolean aBoolean = schedulingService.upScheduling(beginDate, data);
        return new Result<>(2000, "医生的排班更改成功", aBoolean);
    }

}
