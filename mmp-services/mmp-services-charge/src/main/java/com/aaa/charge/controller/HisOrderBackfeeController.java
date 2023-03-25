package com.aaa.charge.controller;

import com.aaa.charge.service.HisOrderBackfeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 退费主表(HisOrderBackfee)表控制层
 *
 * @author makejava
 * @since 2023-03-25 11:09:15
 */
@RestController
@RequestMapping("hisOrderBackfee")
public class HisOrderBackfeeController {
    /**
     * 服务对象
     */
    @Autowired
    private HisOrderBackfeeService hisOrderBackfeeService;

}
