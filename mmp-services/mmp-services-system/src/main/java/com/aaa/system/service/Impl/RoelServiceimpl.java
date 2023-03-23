package com.aaa.system.service.Impl;

import com.aaa.core.entity.Role;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.RoleDao;
import com.aaa.system.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

@Service
public class RoelServiceimpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Override
    public Result<List<Role>> getAll() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("status",0);
        List<Role> sysRoles = roleDao.selectList(wrapper);
        return new Result<>(2000,"查询角色",sysRoles);
    }



    @Override
    public Result<Map<String,Object>> findByuserId(Long userId) {
        List<Role> userRoles=roleDao.selectByuserId(userId);
        QueryWrapper<Role> wrapper=new QueryWrapper<>();
        wrapper.eq("status",0);
        List<Role> roles = roleDao.selectList(wrapper);
        Map<String,Object> map=new HashMap<>();
        map.put("roles",roles);
        map.put("userRoles",userRoles);
        return new Result<>(2000,"查询用户角色",map);
    }

    //开启事务
    @Transactional
    @Override
    public Result<String> confirmRole(Long userId,Long[] roleIds) {
        //给当前用户分配角色    先删除当前用户角色 在加入新的角色
        //删除角色
        roleDao.deleteByuserId(userId);
        //批量添加
        roleDao.batchInsert(userId,roleIds);
        return new Result<>(2000,"分配角色成功");
    }
}
