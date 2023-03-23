package com.aaa.system.controller.api;


import com.aaa.core.entity.Dept;
import com.aaa.core.vo.Result;
import com.aaa.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("system/api/dept")
public class ApiDeptController {
    @Autowired
    public DeptService service;
    @GetMapping("/list")
    public Result<List<Dept>> list(){
        return service.findAll();
    }
}
