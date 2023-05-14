package com.aaa.system.controller.api;


import com.aaa.core.entity.Role;
import com.aaa.core.vo.Result;
import com.aaa.system.service.RoleService;
import com.aaa.system.vo.RoleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("system/api/role")
public class ApiRolerController {
    @Autowired
    public RoleService service;

    @GetMapping("getByuserId/{userId}")
    public Result<Map<String, Object>> getByuserId(@PathVariable Long userId) {
        return service.findByuserId(userId);
    }

    @PostMapping("confirmRole")
    public Result<String> confirmRole(Long userId, Long[] roleIds) {
        return service.confirmRole(userId, roleIds);
    }

    @PostMapping("list/{currentPage}/{pageSize}")
    public Result<IPage<Role>> list(
            @PathVariable Integer currentPage,
            @PathVariable Integer pageSize,
            @RequestBody RoleVo roleVo
    ) {
        return service.getByPage(currentPage, pageSize, roleVo);
    }

    @PostMapping("saveAndUp")
    public Result saveAndUp(Role role) {
        return service.addRole(role);
    }

    @DeleteMapping("{id}")
    public Result del(@PathVariable Long id) {
        return service.delRole(id);
    }

}
