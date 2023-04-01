package com.aaa.statistics.controller;

import com.aaa.core.entity.CheckResult;
import com.aaa.core.vo.CheckResultVo;
import com.aaa.core.vo.Result;
import com.aaa.statistics.service.CheckService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("statistics/api/checkFeign")
public class CheckFeignController {

    @Autowired
    private CheckService checkService;

    @PostMapping("selectAll")
    public Result<List<CheckResult>> selectAll(CheckResultVo checkResultVo){
        return checkService.selectAll(checkResultVo);
    };

    @PostMapping("selectAllTwo")
    public Result<List<CheckResult>> selectAllTwo(CheckResultVo checkResultVo){
        return checkService.selectAllTwo(checkResultVo);
    };
}
