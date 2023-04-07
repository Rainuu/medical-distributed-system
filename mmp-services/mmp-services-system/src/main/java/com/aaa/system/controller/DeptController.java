package com.aaa.system.controller;

import com.aaa.system.service.Impl.DeptServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("system/dept")
public class DeptController {
    @Autowired
    private DeptServiceimpl service;
    @PostMapping("add")
    public boolean add(Long id,Integer num){
        return service.addNum(id, num);
    }
    @PostMapping("jian")
    public boolean jian(Long id,Integer num){
        return service.jianNum(id, num);
    }
}
