package com.aaa.doctor.controller;

import com.aaa.core.entity.CareHistory;
import com.aaa.core.entity.CareOrder;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.Result;
import com.aaa.doctor.service.CareHistoryService;
import com.aaa.doctor.service.CareItemService;
import com.aaa.doctor.service.CareOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/25 10:15
 */
@RestController
@RequestMapping("doctor/patient")
public class CarPatientController {

    @Autowired
    private CareHistoryService careHistoryService;

    @Autowired
    private CareItemService careItemService;

    @Autowired
    private CareOrderService careOrderService;



    /*
     * 根据患者id查询患者信息、患者档案、历史病例
     * */
    @GetMapping("getCarPationId/{patientId}")
    public Result<List<CareHistory>> getCarPationId(@PathVariable String patientId) {
      return careHistoryService.queryCareHistoryByPatientId(patientId);
    }

    // 获取患者的所有信息 患者的病例、每次病例开的处方 和处方的内容
    @GetMapping("CareHistoryByIdAll/{patientId}")
    public Result<List<CareHistory>> getPatientAll(@PathVariable String patientId) {
        List<CareHistory> careHistoryId = careHistoryService.getCareHistoryId(patientId);
        for (CareHistory careHistory : careHistoryId) {
            List<CareOrder> careOrder = careOrderService.getCareOrder(careHistory.getChId());
            careHistory.setCareOrderList(careOrder);
            for (CareOrder order : careOrder) {
                List<CareOrderItem> careOrderItem = careItemService.getCareOrderItem(order.getCoId());
                order.setCareOrderItemList(careOrderItem);
            }
        }
        return new Result<List<CareHistory>>(2000,"查询患者病例所有数据成功",careHistoryId);
    }

}
