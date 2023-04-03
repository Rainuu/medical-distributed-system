package com.aaa.system.service.Impl;

import com.aaa.core.entity.Dept;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.DeptDao;
import com.aaa.system.service.DeptService;
import com.aaa.system.vo.DeptVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public Page getAll(Page page, DeptVo vo) {
        QueryWrapper<Dept> QueryWrapper = new QueryWrapper<>();
        if (vo.getDeptId()!=""&&vo.getDeptId()!=null){
            QueryWrapper.like("dept_id",vo.getDeptId());
        }
        if (vo.getStatus()!=null){
            QueryWrapper.like("status",vo.getStatus());
        }
        if (vo.getBetween()!=null&&vo.getBetween().length == 2){
            QueryWrapper.between("create_time",vo.getBetween()[0],vo.getBetween()[1]);
        }
        Page page1 = deptDao.selectPage(page, QueryWrapper);
        return page1;
    }

    @Override
    public boolean deleById(Long id) {
        int i = deptDao.deleteById(id);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean saveAndUp(Dept dept) {
        int i=-1;

        if (dept.getCreateTime()==null){
            dept.setCreateTime(new Date());
            dept.setUpdateTime(new Date());
        }else {
            dept.setUpdateTime(new Date());
        }
        if (dept.getDeptId()==null){
            i = deptDao.insert(dept);
        }else {
            i = deptDao.updateById(dept);
        }

        if (i>0){
            return true;
        }
        return false;
    }

    /**
     * 通过deptId获取Dept
     * @param deptId
     * @return
     */
    @Override
    public Dept getDeptByDeptId(Long deptId) {
        Dept selectOne = deptDao.selectById(deptId);
        return selectOne;
    }
}
