package com.aaa.charge.controller.api;

import com.aaa.charge.service.HisOrderChargeService;
import com.aaa.charge.util.MyAlipayUtil;
import com.aaa.charge.vo.OrderChargeVo;
import com.aaa.charge.vo.PostObjVo;
import com.aaa.core.entity.*;
import com.aaa.core.vo.Result;
import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 收费表(HisOrderCharge)表控制层
 */
@RestController
@RequestMapping("charge/api/hisOrderCharge")
public class ApiHisOrderChargeController {
    /**
     * 服务对象
     */
    @Autowired
    private HisOrderChargeService service;

    //收费列表,分页获取所有数据库数据
    @PostMapping("list/{curr}/{size}")
    public Result<IPage<OrderCharge>> list(
            @PathVariable Integer curr,
            @PathVariable Integer size,
            @RequestBody OrderChargeVo chargeVo

    ) {
        return service.findAll(curr, size, chargeVo);
    }

    //收费列表:微信支付
    @PostMapping("updateBystatus/{orderId}")
    public Result<List<CareOrder>> updateBystatus(@PathVariable String orderId) {
        return service.updateBystatus(orderId);
    }

    // 收费列表,现金收费
    @GetMapping("update/{orderId}")
    public Result<List<OrderCharge>> upd(@PathVariable String orderId) {
        return service.updByStatusType(orderId);
    }

    //收费列表,查看详情
    @GetMapping("detail/{orderId}")
    public Result<List<OrderChargeItem>> detail(@PathVariable String orderId) {
        return service.finddetail(orderId);
    }


    //处方收费:微信支付按钮实现
    @PostMapping("carateNative")
    public Result<List<CareOrderItem>> carateNative(@RequestBody PostObjVo postObjVo) {
        return service.ZFB(postObjVo);
    }

    //处方收费,微信支付,支付成功后修改状态
    @PostMapping("updstatus/{orderId}")
    public Result<List<OrderCharge>> updstatus(@PathVariable String orderId) {
        return service.updByStatus(orderId);
    }

    //调用fen接口实现字典功能
    @GetMapping("dictionary")
    public Result<List<DictData>> dictionary() {
        return service.CharFeign();
    }


    //处方发药查询所有数据
    @GetMapping("listhand/{regId}")
    public Result<Map<String, Object>> list(@PathVariable String regId) {
        return service.selectupdById(regId);
    }

    //处方发药  发药按钮实现
    @GetMapping("updByDispense/{itemId}")
    public Result updByDispense(@PathVariable String[] itemId) {
        return service.updByDispense(itemId);
    }


    //支付宝沙盒支付,未完成
    @PostMapping("pay")
    public Result payForIt(@RequestBody OrderCharge orderCharge) throws AlipayApiException {
        String orderForm = MyAlipayUtil.createOrderForm(orderCharge.getOrderId(), orderCharge.getOrderAmount().toString(), orderCharge.getPatientName());
        return new Result(200, "支付成功", orderForm);
    }

}