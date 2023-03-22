package com.aaa.system.controller;

import com.aaa.core.entity.User;
import com.aaa.system.service.UserSercvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserSercvice userSercvice;
    @GetMapping("/getByUsername/{username}")
    public User getByUsername(@PathVariable String username){
        return userSercvice.findByUsername(username);
    }
}
