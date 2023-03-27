package com.aaa.charge.controller.api;

import com.aaa.charge.fegin.OrderCharFeign;
import com.aaa.charge.service.HisOrderChargeService;
import com.aaa.charge.vo.OrderChargeVo;
import com.aaa.core.entity.DictData;
import com.aaa.core.entity.OrderCharge;
import com.aaa.core.entity.OrderChargeItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收费表(HisOrderCharge)表控制层
 *
 * @author makejava
 * @since 2023-03-24 09:56:25
 */
@RestController
@RequestMapping("charge/api/hisOrderCharge")
public class ApiHisOrderChargeController {
    /**
     * 服务对象
     */
    @Autowired
    private HisOrderChargeService service;

    //分页获取所有数据库数据
    @PostMapping("list/{curr}/{size}")
    public Result<IPage<OrderCharge>> list(
            @PathVariable Integer curr,
            @PathVariable Integer size,
            @RequestBody OrderChargeVo chargeVo

    ){
        return  service.findAll(curr,size,chargeVo);
    }

    //分页获取所有数据库数据
    @GetMapping ("detail/{orderId}")
    public Result<List<OrderChargeItem>> detail(@PathVariable String orderId){

        return  service.finddetail(orderId);
    }

    //调用fen接口实现字典功能
    @GetMapping("dictionary")
    public Result<List<DictData>> dictionary(){
        return service.CharFeign();
    }
    //现金支付
    @GetMapping("update/{orderId}")
    public Result<List<OrderCharge>> upd(@PathVariable String orderId){
        return service.updByStatusType(orderId);
    }



}