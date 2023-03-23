package com.aaa.system.service;


import com.aaa.core.entity.User;
import com.aaa.core.vo.Result;
import com.aaa.system.vo.UserVo;
import com.aaa.system.vo.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface UserSercvice {
    public User findByUsername(String username);
    public Result<User> getByPhoneInfo();

    Result<IPage<User>> findByCoodtion(Integer curr, Integer size, com.aaa.system.vo.UserVo userVo);
}
