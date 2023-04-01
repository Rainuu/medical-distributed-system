package com.aaa.charge.controller;

import com.aaa.charge.service.HisOrderChargeService;
import com.aaa.core.entity.OrderCharge;
import com.aaa.core.entity.OrderChargeItem;
import com.aaa.core.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    @PostMapping("list")
    public List<OrderCharge> list(@RequestBody String[] create_time){
        return hisOrderChargeService.listAll(create_time);
    }
    @PostMapping("listAllItem")
    public List<OrderChargeItem> listAllItem(@RequestBody String[] create_time){
        return hisOrderChargeService.listAllItem(create_time);
    }


}
