package com.aaa.doctor.controller;

import com.aaa.core.entity.CareHistory;
import com.aaa.core.entity.CareOrder;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.entity.Registration;
import com.aaa.core.vo.Result;
import com.aaa.doctor.service.CareHistoryService;
import com.aaa.doctor.service.CareItemService;
import com.aaa.doctor.service.CareOrderService;
import com.aaa.doctor.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private RegistrationService registrationService;



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


    /**
     * 查询新开就诊中的就诊列表状态 ------>  待就诊状态
     * @param status
     * @return
     */
    @PostMapping("queryRegistrationStatus1/{status}")
    public Result queryRegistrationStatus1(@PathVariable String status) {
        List<Registration> registrations = registrationService.queryRegistrationStatus1(status);
        return new Result<>(2000,"查询成功",registrations);
    }

}
