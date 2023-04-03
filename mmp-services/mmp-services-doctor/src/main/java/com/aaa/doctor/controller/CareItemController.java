package com.aaa.doctor.controller;

import com.aaa.core.entity.CareHistory;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.Result;
import com.aaa.doctor.service.CareHistoryService;
import com.aaa.doctor.service.CareItemService;
import com.aaa.doctor.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/25 18:05
 */
@RestController
@RequestMapping("doctor/patient")
public class CareItemController {
    @Autowired
    private CareItemService careItemService;

    @Autowired
    private CareHistoryService careHistoryService;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("FindByItem/{patientId}/{chId}")
    public Result<List<CareOrderItem>> FindByItem(@PathVariable String patientId,@PathVariable String chId) {

        return careItemService.FindByItem(patientId,chId);

    }


    /**
     * 保存病历
     */
    @PostMapping("SaveHistroy/{registrationId}")
    public Result<CareHistory> SaveHistroy(@PathVariable String registrationId,@RequestBody CareHistory careHistory) {
        return careHistoryService.SaveHistroy(registrationId,careHistory);
    }



    //获取挂号编号
    @PostMapping("queryDeptNumber/{deptId}")
    public Result queryDeptNumber(@PathVariable Long deptId){
        Integer regNumber = registrationService.getRagNumber(deptId);
        return new Result(2000,"查询符合条件的挂号单成功",regNumber);
    }

    /**
     * 根据id删除未支付的订单
     */
    @GetMapping("deleteCareOrderItemById/{itemId}/{amount}")
    public Result<Boolean> deleteCareOrderItemById(@PathVariable String itemId,@PathVariable BigDecimal amount ) {
        boolean booleanResult = careHistoryService.deleteCareOrderItemById(itemId, amount);
        return new Result<Boolean>(2000,"删除未支付订单成功",booleanResult);
    }


}
