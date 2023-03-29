package com.aaa.system.controller;

import com.aaa.core.entity.User;
import com.aaa.system.service.UserSercvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserSercvice userSercvice;
    @GetMapping("/getByUsername/{username}")
    public User getByUsername(@PathVariable String username){
        return userSercvice.findByUsername(username);
    }

    @PostMapping("/queryUsersNeedScheduling")
    public List<User> queryUsersNeedScheduling(@RequestBody Map<String,Object> map){
        return userSercvice.queryUserScheduling(map);
    };
}
