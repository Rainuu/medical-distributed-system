package com.aaa.statistics.controller;

import com.aaa.core.vo.Result;
import com.aaa.statistics.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("statistics/api/revenue")
public class RevenueController {
    @Autowired
    private RevenueService service;

    @PostMapping("getInfo")
    public Result getInfo(String dateRange1, String dateRange2) {
        return service.queryInfo(dateRange1, dateRange2);
    }
}
