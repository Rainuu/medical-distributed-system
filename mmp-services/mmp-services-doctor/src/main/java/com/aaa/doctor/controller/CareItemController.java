package com.aaa.doctor.controller;

import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.Result;
import com.aaa.doctor.service.CareItemService;
import org.apache.ibatis.annotations.Param;
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
 * @date 2023/3/25 18:05
 */
@RestController
@RequestMapping("doctor/patient")
public class CareItemController {
    @Autowired
    private CareItemService careItemService;

    @GetMapping("FindByItem/{patientId}/{chId}")
    public Result<List<CareOrderItem>> FindByItem(@PathVariable String patientId,@PathVariable String chId) {

        System.out.println("========="+patientId);
        System.out.println("========="+chId);
        return careItemService.FindByItem(patientId,chId);


    }
}
