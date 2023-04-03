package com.aaa.charge.controller;

import com.aaa.charge.service.HisCareOrderItemService;
import com.aaa.core.entity.CareOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("charge/hisCareOrderItem")
public class HisCareOrderItemController {
    @Autowired
    private HisCareOrderItemService service;

    @PostMapping("list")
    public List<CareOrderItem> list(String itemName, String dateRange1,String dateRange2){
        return service.getInfo(itemName,dateRange1,dateRange2);
    }
    @PostMapping("getData")
    public List<Map<String,Object>> getData(String itemName,String dateRange1,String dateRange2){
       return service.getData(itemName,dateRange1,dateRange2);
    }
}
