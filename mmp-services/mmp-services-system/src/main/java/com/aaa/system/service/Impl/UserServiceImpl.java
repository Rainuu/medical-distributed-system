package com.aaa.system.service.Impl;

import com.aaa.core.entity.User;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.UserDao;
import com.aaa.system.service.UserSercvice;
import com.aaa.system.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserSercvice{
    @Autowired
    private UserDao dao;
    @Autowired
    private PasswordEncoder passwordEncoder;
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

    @Override
    public Result<IPage<User>> findByCoodtion(Integer curr, Integer size, UserVo userVo) {
        IPage<User> page = new Page<>(curr,size);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(userVo.getUserName())){
            wrapper.like("user_name",userVo.getUserName());
        }
        if (StringUtils.hasText(userVo.getPhone())){
            wrapper.like("phone",userVo.getPhone());
        }
        if (Objects.nonNull(userVo.getDeptId())){
            wrapper.eq("dept_id",userVo.getDeptId());
        }
        if (Objects.nonNull(userVo.getStatus())){
            wrapper.eq("status",userVo.getStatus());
        }
        if (Objects.nonNull(userVo.getDateRange())&&userVo.getDateRange().length==2){
            wrapper.between("create_time",userVo.getDateRange()[0],userVo.getDateRange()[1]);
        }
        IPage<User> page1 = dao.selectPage(page,wrapper);

        return new Result<IPage<User>>(200,"查询用户成功",page1);
    }

    @Override
    public boolean deleById(Long id) {
        int i = dao.deleteById(id);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean saveAndUp(User user) {
        int i=-1;

        if (user.getCreateTime()==null){
            user.setCreateTime(new Date());
            user.setPassword(passwordEncoder.encode("123456"));
            user.setUpdateTime(new Date());
        }else {
            user.setUpdateTime(new Date());
        }
        if (user.getUserId()==null){
             i = dao.insert(user);
        }else {
             i = dao.updateById(user);
        }

        if (i>0){
            return true;
        }
        return false;
    }
}
