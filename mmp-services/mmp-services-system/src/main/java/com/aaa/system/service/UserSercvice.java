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

    User queryById(Long id);

    List<User> queryUserScheduling(Map<String, Object> map);

    Result checkPassWord(String password);

    Result upPassword(String newPassword);

    Result exitUser();

    //通过userId获取User
    User getUserByUserId(Long userId);
}
