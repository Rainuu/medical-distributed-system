package com.aaa.charge.controller;

import com.aaa.charge.service.HisCareHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 病例表(HisCareHistory)表控制层
 */
@RestController
@RequestMapping("hisCareHistory")
public class HisCareHistoryController {
    /**
     * 服务对象
     */
    @Autowired
    private HisCareHistoryService hisCareHistoryService;


}
