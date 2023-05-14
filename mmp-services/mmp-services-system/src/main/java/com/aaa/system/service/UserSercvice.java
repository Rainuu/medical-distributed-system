package com.aaa.system.service;


import com.aaa.core.entity.User;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

public interface UserSercvice {
    User findByUsername(String username);

    Result<User> getByPhoneInfo();

    Result<IPage<User>> findByCoodtion(Integer curr, Integer size, com.aaa.system.vo.UserVo userVo);

    boolean deleById(Long id);

    boolean saveAndUp(User user);

    User queryById(Long id);

    List<User> queryUserScheduling(Map<String, Object> map);

    Result checkPassWord(String password);

    Result upPassword(String newPassword);

    Result exitUser();

    //通过userId获取User
    User getUserByUserId(Long userId);
}
