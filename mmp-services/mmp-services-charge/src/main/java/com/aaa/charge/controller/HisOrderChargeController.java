package com.aaa.charge.controller;

import com.aaa.charge.service.HisOrderChargeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 收费表(HisOrderCharge)表控制层
 *
 * @author makejava
 * @since 2023-03-24 09:56:25
 */
@RestController
@RequestMapping("charge/hisOrderCharge")
public class HisOrderChargeController {
    /**
     * 服务对象
     */
    @Autowired
    private HisOrderChargeService hisOrderChargeService;

}
