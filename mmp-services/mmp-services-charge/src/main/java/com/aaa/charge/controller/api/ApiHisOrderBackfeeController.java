package com.aaa.charge.controller.api;

import com.aaa.charge.service.HisOrderBackfeeService;
import com.aaa.charge.vo.OrderBackfeeVo;
import com.aaa.charge.vo.OrderChargeVo;
import com.aaa.core.entity.DictData;
import com.aaa.core.entity.OrderBackfee;
import com.aaa.core.entity.OrderBackfeeItem;
import com.aaa.core.entity.OrderCharge;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 退费主表(HisOrderBackfee)表控制层
 *
 * @author makejava
 * @since 2023-03-25 11:09:15
 */
@RestController
@RequestMapping("charge/api/hisOrderBackfee")
public class ApiHisOrderBackfeeController {
    /**
     * 服务对象
     */
    @Autowired
    private HisOrderBackfeeService service;

    //分页获取所有数据库数据
    @PostMapping("list/{curr}/{size}")
    public Result<IPage<OrderBackfee>> list(
            @PathVariable Integer curr,
            @PathVariable Integer size,
            @RequestBody OrderBackfeeVo backfeeVo
    ){
        return  service.findAll(curr,size,backfeeVo);
    }

    @GetMapping("detail/{backId}")
    public Result<List<OrderBackfeeItem>> detail(@PathVariable String backId){
        return service.selectAll(backId);
    }

    @GetMapping("/dictionary")
    public Result dictionary(){
        return service.selectdictionary();
    }
}