package com.aaa.system.service;


import com.aaa.core.entity.User;
import com.aaa.core.vo.Result;

public interface UserSercvice {
    public User findByUsername(String username);
    public Result<User> getByPhoneInfo();
}
