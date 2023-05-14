package com.aaa.system.controller;

import com.aaa.core.entity.LoginInfo;
import com.aaa.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("system/loginInfo")
public class LoginInfoController {
    @Autowired
    private LoginLogService service;

    @PostMapping("addLoginLog")
    public int addLoginLog(@RequestBody LoginInfo loginInfo) {
        System.out.println("===============" + loginInfo.toString());
        return service.AopLoginLog(loginInfo);
    }
}
