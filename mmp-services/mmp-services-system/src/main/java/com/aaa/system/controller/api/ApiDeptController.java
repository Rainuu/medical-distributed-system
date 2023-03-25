package com.aaa.system.controller.api;


import com.aaa.core.entity.Dept;
import com.aaa.core.vo.Result;
import com.aaa.system.service.DeptService;
import com.aaa.system.vo.DeptVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("getAll")
    public Result<Page> getAll(Page page, DeptVo vo){

        return new Result<Page>(200,"",service.getAll(page,vo));
    }
    @PostMapping("add")
    public Result addDept(Dept dept){
        return new Result<>(200,null,service.saveAndUp(dept));
    }
    @DeleteMapping("{id}")
    public Result delById(@PathVariable Long id){
        return new Result<>(200,null,service.deleById(id));
    }
}
