package com.aaa.system.service.Impl;

import com.aaa.core.entity.Role;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.RoleDao;
import com.aaa.system.service.RoleService;
import com.aaa.system.vo.RoleVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class RoelServiceimpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Result<List<Role>> getAll() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        List<Role> sysRoles = roleDao.selectList(wrapper);
        return new Result<>(2000, "查询角色", sysRoles);
    }


    @Override
    public Result<Map<String, Object>> findByuserId(Long userId) {
        List<Role> userRoles = roleDao.selectByuserId(userId);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        List<Role> roles = roleDao.selectList(wrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("roles", roles);
        map.put("userRoles", userRoles);
        return new Result<>(2000, "查询用户角色", map);
    }

    //开启事务
    @Transactional
    @Override
    public Result<String> confirmRole(Long userId, Long[] roleIds) {
        //给当前用户分配角色    先删除当前用户角色 在加入新的角色
        //删除角色
        roleDao.deleteByuserId(userId);
        //批量添加
        roleDao.batchInsert(userId, roleIds);
        return new Result<>(2000, "分配角色成功");
    }

    @Override
    public Result<IPage<Role>> getByPage(Integer currentPage, Integer pageSize, RoleVo roleVo) {
        IPage<Role> page = new Page<>(currentPage, pageSize);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(roleVo.getRoleName())) {
            wrapper.like("role_name", roleVo.getRoleName());
        }
        if (StringUtils.hasText(roleVo.getRoleCode())) {
            wrapper.eq("role_code", roleVo.getRoleCode());
        }
        if (Objects.nonNull(roleVo.getStatus())) {
            wrapper.eq("status", roleVo.getStatus());
        }
        if (Objects.nonNull(roleVo.getDateRange()) && roleVo.getDateRange().length == 2) {
            wrapper.between("create_time", roleVo.getDateRange()[0], roleVo.getDateRange()[1]);
        }
        IPage<Role> page1 = roleDao.selectPage(page, wrapper);
        return new Result<>(2000, "查询角色", page1);
    }

    @Override
    public Result addRole(Role role) {
        String token = WebUtil.getRequest().getHeader("token");
        Map<String, Object> tokenChaim = JwtUtil.getTokenChaim(token);
        if (role.getRoleId() == null) {
            role.setCreateTime(new Date());
            role.setUpdateTime(new Date());
            role.setCreateBy((String) tokenChaim.get("username"));
            role.setUpdateBy((String) tokenChaim.get("username"));

            int insert = roleDao.insert(role);
            if (insert > 0) {
                return new Result(200, null, true);
            } else {
                return new Result(200, null, false);
            }
        } else {
            role.setUpdateBy((String) tokenChaim.get("username"));
            role.setUpdateTime(new Date());
            int i = roleDao.updateById(role);
            if (i > 0) {
                return new Result(200, null, true);
            } else {
                return new Result(200, null, false);
            }
        }

    }

    @Override
    public Result delRole(Long id) {
        List<Integer> list = roleDao.selectUserByRole(id);
        if (list.size() == 0) {
            List<Integer> list1 = roleDao.selectMenuByRole(id);
            if (list1.size() == 0) {
                int del = roleDao.deleteById(id);
                if (del > 0) {
                    return new Result(200, "删除成功", true);
                }
                return new Result(500, "删除失败", false);
            } else {
                return new Result(200, "无法删除，该角色已绑定菜单权限");
            }
        } else {
            return new Result(200, "无法删除，该角色已被用户使用");
        }
    }

}
