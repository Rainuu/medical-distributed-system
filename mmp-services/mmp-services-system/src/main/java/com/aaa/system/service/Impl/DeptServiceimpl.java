package com.aaa.system.service.Impl;

import com.aaa.core.entity.Dept;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.DeptDao;
import com.aaa.system.service.DeptService;
import com.aaa.system.vo.DeptVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceimpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public Result<List<Dept>> findAll() {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        List<Dept> list = deptDao.selectList(wrapper);
        return new Result<List<Dept>>(2000, "查询所有部门", list);
    }

    @Override
    public Page getAll(Page page, DeptVo vo) {
        QueryWrapper<Dept> QueryWrapper = new QueryWrapper<>();
        if (vo.getDeptId() != "" && vo.getDeptId() != null) {
            QueryWrapper.like("dept_id", vo.getDeptId());
        }
        if (vo.getStatus() != null) {
            QueryWrapper.like("status", vo.getStatus());
        }
        if (vo.getBetween() != null && vo.getBetween().length == 2) {
            QueryWrapper.between("create_time", vo.getBetween()[0], vo.getBetween()[1]);
        }
        Page page1 = deptDao.selectPage(page, QueryWrapper);
        return page1;
    }

    @Override
    public boolean deleById(Long id) {
        int i = deptDao.deleteById(id);
        return i > 0;
    }

    @Override
    public boolean saveAndUp(Dept dept) {
        int i = -1;

        if (dept.getCreateTime() == null) {
            dept.setCreateTime(LocalDateTime.now());
            dept.setUpdateTime(LocalDateTime.now());
        } else {
            dept.setUpdateTime(LocalDateTime.now());
        }
        if (dept.getDeptId() == null) {
            i = deptDao.insert(dept);
        } else {
            i = deptDao.updateById(dept);
        }

        return i > 0;
    }

    /**
     * 通过deptId获取Dept
     *
     * @param deptId
     * @return
     */
    @Override
    public Dept getDeptByDeptId(Long deptId) {
        Dept selectOne = deptDao.selectById(deptId);
        return selectOne;
    }

    @Override
    public boolean addNum(Long id, Integer num) {
        Dept dept = deptDao.selectById(id);
        Integer regNumber = dept.getRegNumber();
        Dept dept1 = new Dept();
        dept1.setDeptId(id);
        dept1.setRegNumber(regNumber + num);
        int i = deptDao.updateById(dept1);
        return i > 0;
    }

    @Override
    public boolean jianNum(Long id, Integer num) {
        Dept dept = deptDao.selectById(id);
        Integer regNumber = dept.getRegNumber();
        Dept dept1 = new Dept();
        dept1.setDeptId(id);
        if (regNumber - num >= 0) {
            dept1.setRegNumber(regNumber - num);
        } else {
            return false;
        }

        int i = deptDao.updateById(dept1);
        return i > 0;
    }

    /**
     * 通过deptId更新挂号量
     *
     * @param deptId
     * @param regNum
     * @return
     */
    @Override
    public Boolean updDeptRegNumByDeptId(Long deptId, Integer regNum) {
        UpdateWrapper<Dept> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("dept_id", deptId);
        updateWrapper.set("reg_number", regNum);
        int update = deptDao.update(null, updateWrapper);
        boolean b = update == 1;
        return b;
    }
}
