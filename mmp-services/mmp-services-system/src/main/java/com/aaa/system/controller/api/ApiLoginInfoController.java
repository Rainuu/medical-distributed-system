package com.aaa.system.controller.api;

import com.aaa.core.vo.Result;
import com.aaa.system.service.LoginLogService;
import com.aaa.system.vo.LoginInfoVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system/api/loginInfo")
public class ApiLoginInfoController {
    @Autowired
    private LoginLogService service;

    @PostMapping("getAll")
    public Result getAll(Page page, LoginInfoVo loginInfoVo) {
        return service.getAll(page, loginInfoVo);
    }

    @DeleteMapping("{id}")
    public Result delById(@PathVariable Long id) {
        return service.delLoginInfo(id);
    }

    @GetMapping("clean")
    public Result clean() {
        return service.clean();
    }
}
