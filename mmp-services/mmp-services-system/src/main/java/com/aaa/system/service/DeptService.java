package com.aaa.system.service;

import com.aaa.core.entity.Dept;
import com.aaa.core.vo.Result;
import com.aaa.system.vo.DeptVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


public interface DeptService {
    Result<List<Dept>> findAll();

    Page getAll(Page page, DeptVo vo);

    boolean deleById(Long id);

    boolean saveAndUp(Dept dept);


    //通过deptId获取Dept
    Dept getDeptByDeptId(Long deptId);

    boolean addNum(Long id, Integer num);

    boolean jianNum(Long id, Integer num);

    //通过deptId更新挂号量
    Boolean updDeptRegNumByDeptId(Long deptId, Integer regNum);
}
