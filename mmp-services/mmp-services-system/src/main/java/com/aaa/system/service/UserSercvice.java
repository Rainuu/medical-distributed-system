package com.aaa.system.service;


import com.aaa.core.entity.User;
import com.aaa.core.vo.Result;
import com.aaa.system.vo.UserVo;
import com.aaa.system.vo.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

public interface UserSercvice {
    public User findByUsername(String username);
    public Result<User> getByPhoneInfo();

    Result<IPage<User>> findByCoodtion(Integer curr, Integer size, com.aaa.system.vo.UserVo userVo);

    public boolean deleById(Long id);
    public boolean saveAndUp(User user);


    List<User> queryUserScheduling(Map<String, Object> map);
}
