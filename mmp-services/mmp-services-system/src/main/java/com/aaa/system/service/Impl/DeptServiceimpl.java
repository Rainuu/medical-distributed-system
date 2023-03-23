package com.aaa.system.service.Impl;

import com.aaa.core.entity.Dept;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.DeptDao;
import com.aaa.system.service.DeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeptServiceimpl implements DeptService {
    @Autowired
    private DeptDao deptDao;
    @Override
    public Result<List<Dept>> findAll() {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.eq("status",0);
        List<Dept> list =  deptDao.selectList(wrapper);
        return new Result<List<Dept>>(2000,"查询所有部门",list);
    }
}
