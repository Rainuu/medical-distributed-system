package com.aaa.system.service;

import com.aaa.core.entity.Role;
import com.aaa.core.vo.Result;
import com.aaa.system.vo.RoleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Result<List<Role>> getAll();

    Result<Map<String, Object>> findByuserId(Long userId);

    Result<String> confirmRole(Long userId, Long[] roleIds);

    Result<IPage<Role>> getByPage(Integer currentPage, Integer pageSize, RoleVo roleVo);

    Result addRole(Role role);

    Result delRole(Long id);
}
