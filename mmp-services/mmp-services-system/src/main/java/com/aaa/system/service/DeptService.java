package com.aaa.system.service;

import com.aaa.core.entity.Dept;
import com.aaa.core.entity.User;
import com.aaa.core.vo.Result;
import com.aaa.system.vo.DeptVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


public interface DeptService {
    Result<List<Dept>> findAll();

    Page getAll(Page page, DeptVo vo);

    public boolean deleById(Long id);
    public boolean saveAndUp(Dept dept);
}
