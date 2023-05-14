package com.example.dao;

import com.example.model.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String name);

    int insert(Role record);

    Role selectByPrimaryKey(String name);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
}