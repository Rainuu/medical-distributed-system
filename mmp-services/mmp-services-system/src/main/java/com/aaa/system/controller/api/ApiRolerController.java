package com.aaa.system.controller.api;


import com.aaa.core.entity.Role;
import com.aaa.core.vo.Result;
import com.aaa.system.service.RoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("system/api/role")
public class ApiRolerController {
    @Autowired
    public RoleService service;
    @GetMapping("/list")
    public Result<List<Role>> Rlist(){
        return service.getAll();
    }
    @GetMapping("getByuserId/{userId}")
    public Result<Map<String,Object>> getByuserId(@PathVariable Long userId){
        return service.findByuserId(userId);
    }

    @PostMapping("confirmRole")
    public Result<String> confirmRole(Long userId,Long[] roleIds){
       return service.confirmRole(userId,roleIds);
    }

    @PostMapping("")
    public Result<IPage<Role>> list (){
        return null;
    }


}
