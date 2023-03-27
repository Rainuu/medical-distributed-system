package com.aaa.charge.controller;

import com.aaa.charge.service.HisCareHistoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 病例表(HisCareHistory)表控制层
 *
 * @author makejava
 * @since 2023-03-27 09:37:34
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
