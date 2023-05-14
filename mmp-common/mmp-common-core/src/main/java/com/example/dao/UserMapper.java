package com.example.dao;

import com.example.model.User;

import java.util.List;

public interface UserMapper {
    int insert(User record);

    List<User> selectAll();
}