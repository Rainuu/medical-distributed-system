package com.aaa.system.service;

import com.aaa.core.entity.Dept;
import com.aaa.core.vo.Result;

import java.util.List;


public interface DeptService {
    Result<List<Dept>> findAll();
}
