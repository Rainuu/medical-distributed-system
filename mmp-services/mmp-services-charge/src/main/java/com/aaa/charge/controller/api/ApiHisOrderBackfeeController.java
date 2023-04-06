package com.aaa.charge.controller.api;

import com.aaa.charge.service.HisOrderBackfeeItemService;
import com.aaa.charge.service.HisOrderBackfeeService;
import com.aaa.charge.vo.OrderBackfeeVo;
import com.aaa.charge.vo.OrderChargeVo;
import com.aaa.charge.vo.PostObjVo;
import com.aaa.charge.vo.PostObjVoV;
import com.aaa.core.entity.*;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private HisOrderBackfeeItemService hisOrderBackfeeItemService;



    //分页获取所有数据库数据
    @PostMapping("list/{curr}/{size}")
    public Result<IPage<OrderBackfee>> list(
            @PathVariable Integer curr,
            @PathVariable Integer size,
            @RequestBody OrderBackfeeVo backfeeVo
    ){
        return  service.findAll(curr,size,backfeeVo);
    }
    //获取所有数据
    @GetMapping("detail/{backId}")
    public Result<List<OrderBackfeeItem>> detail(@PathVariable String backId){
        return service.selectAll(backId);
    }
    //调用fen接口实现字典功能
    @GetMapping("/dictionary")
    public Result dictionary(){
        return service.selectdictionary();
    }



    //处方退费
    @GetMapping("list/{regId}")
    public Result<Map<String,Object>> list(@PathVariable String regId)  {
        return  service.selectByregId(regId);
    }
    //处方退费：现金退费
    @PostMapping("updateBystatus")
    public Result<List<OrderBackfeeItem>> updateBystatus(@RequestBody PostObjVoV postObjVoV){
        return hisOrderBackfeeItemService.updateBystatus(postObjVoV);
    }
    //处方退费: 支付宝退费
    @PostMapping("updBystatus")
    public Result<List<OrderBackfeeItem>> updBystatus(@RequestBody PostObjVoV postObjVoV){
        return hisOrderBackfeeItemService.updBystatus(postObjVoV);
    }



}