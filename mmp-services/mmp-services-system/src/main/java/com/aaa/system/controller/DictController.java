package com.aaa.system.controller;

import com.aaa.core.entity.DictData;
import com.aaa.system.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/dict")
public class DictController {
    @Autowired
    private DictDataService service;

    @GetMapping("getAll")
    public List<DictData> getAll() {
        System.out.println("---------------------");
        return service.getall();
    } //处理字典
}
