package com.aaa.system.controller.api;


import com.aaa.core.entity.DictData;
import com.aaa.core.vo.Result;
import com.aaa.system.service.DictDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("system/api/dict/data")
public class ApiDictDataController {
    @Autowired
    public DictDataService service;
    @GetMapping("findByType/{type}")
    public Result<List<DictData>> list(@PathVariable String type){
        return service.findByType(type);
    }
    @GetMapping("getall")
    public Result<List<DictData>> getAll(){return service.getall();};
}
