package com.aaa.statistics.controller;

import com.aaa.core.entity.Registration;
import com.aaa.core.vo.RegistrationVo;
import com.aaa.core.vo.Result;
import com.aaa.statistics.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("statistics/api/workFeign")
public class WorkFeignController {

    @Autowired
    private WorkService workService;

    @PostMapping("queryByDoctor")
    public Result<List<Registration>> queryByDoctor(RegistrationVo registrationVo){
       return workService.selectAll(registrationVo);
    }
    @PostMapping("queryByDoctor2")
    public Result<List<Registration>> queryByDoctor2(RegistrationVo registrationVo){
        return workService.selectAllTwo(registrationVo);
    }


}
