package com.aaa.doctor.controller;

import com.aaa.core.entity.CareHistory;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.Result;
import com.aaa.doctor.service.CareHistoryService;
import com.aaa.doctor.service.CareItemService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("FindByItem/{patientId}/{chId}")
    public Result<List<CareOrderItem>> FindByItem(@PathVariable String patientId,@PathVariable String chId) {

        return careItemService.FindByItem(patientId,chId);

    }


    /**
     * 保存病历
     */
    @PostMapping("SaveHistroy")
    public Result<String> SaveHistroy(@RequestBody CareHistory careHistory) {
        return careHistoryService.SaveHistroy(careHistory);
    }
}
