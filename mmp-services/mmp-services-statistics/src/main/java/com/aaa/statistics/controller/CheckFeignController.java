package com.aaa.statistics.controller;

import com.aaa.core.entity.CheckResult;
import com.aaa.core.entity.Registration;
import com.aaa.core.vo.CheckResultVo;
import com.aaa.core.vo.RegistrationVo;
import com.aaa.core.vo.Result;
import com.aaa.statistics.service.CheckService;
import com.aaa.statistics.service.WorkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("statistics/api/checkFeign")
public class CheckFeignController {

    @Autowired
    private CheckService checkService;

    @PostMapping("selectAll")
    public Result<List<CheckResult>> selectAll(String patientName, String checkItemId,String[] dateRange){
        CheckResultVo checkResultVo = new CheckResultVo();
        checkResultVo.setPatientName(patientName);
        checkResultVo.setCheckItemId(checkItemId);
        checkResultVo.setDateRange(dateRange);
        System.out.println(checkResultVo);
        return checkService.selectAll(checkResultVo);
    };

    @PostMapping("selectAllTwo")
    public Result<List<CheckResult>> selectAllTwo(CheckResultVo checkResultVo){
        return checkService.selectAllTwo(checkResultVo);
    };
}
