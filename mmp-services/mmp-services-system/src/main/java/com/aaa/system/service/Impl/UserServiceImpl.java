package com.aaa.system.service.Impl;

import com.aaa.core.entity.User;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.UserDao;
import com.aaa.system.service.UserSercvice;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserSercvice {
    @Autowired
    private UserDao dao;
    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> wrapper =new QueryWrapper<>();
        wrapper.eq("phone",username);
        wrapper.eq("status",0);
        return dao.selectOne(wrapper);
    }

    @Override
    public Result<User> getByPhoneInfo() {
        String token = WebUtil.getRequest().getHeader("token");
        Map<String, Object> info = JwtUtil.getTokenChaim(token);
        String phone= (String) info.get("username");
        QueryWrapper<User> wrapper =new QueryWrapper<>();
        wrapper.eq("phone",phone);
        wrapper.eq("status",0);
        User user = dao.selectOne(wrapper);
        return new Result<User>(200,"查询成功",user);
    }
}
