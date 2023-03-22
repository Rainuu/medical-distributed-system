package com.aaa.system.controller.api;

import com.aaa.core.entity.User;
import com.aaa.core.vo.Result;
import com.aaa.system.service.UserSercvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("system/api/user")
public class ApiUserController {
    @Autowired
    private UserSercvice userSercvice;
    @GetMapping("/getInfo")
    public Result<User> getInfo(){

        return userSercvice.getByPhoneInfo();
    }

}
