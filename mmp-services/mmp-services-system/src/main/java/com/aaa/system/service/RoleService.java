package com.aaa.system.service;

import com.aaa.core.entity.Role;
import com.aaa.core.vo.Result;


import java.util.List;
import java.util.Map;

public interface RoleService {

    Result<List<Role>> getAll();

    Result<Map<String,Object>> findByuserId(Long userId);

    Result<String> confirmRole(Long userId, Long[] roleIds);
}
