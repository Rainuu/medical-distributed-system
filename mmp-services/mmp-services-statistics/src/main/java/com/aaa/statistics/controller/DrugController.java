package com.aaa.statistics.controller;

import com.aaa.core.vo.Result;
import com.aaa.statistics.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("statistics/api/durg")
public class DrugController {
    @Autowired
    private DrugService service;

    @PostMapping("list")
    public Result list(String drugName,String dateRange1,String dateRange2){
     return new Result(200,null,service.alist(drugName,dateRange1,dateRange2));
    }
    @PostMapping("getData")
    public Result getData(String drugName,String dateRange1,String dateRange2){
        return new Result(200,null,service.getData(drugName,dateRange1,dateRange2));
    }
}
