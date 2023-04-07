package com.aaa.system.controller;

import com.aaa.system.service.Impl.DeptServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system/dept")
public class DeptController {
    @Autowired
    private DeptServiceimpl service;
    @PostMapping("add")
    public boolean add(Long id,Integer num){
        return service.addNum(id, num);
    }
    @PostMapping("jian")
    public boolean jian(Long id,Integer num){
        return service.jianNum(id, num);
    }

    /**
     * 通过deptId更新挂号量
     * @param deptId
     * @param regNum
     * @return
     */
    @GetMapping("updDeptRegNumByDeptId/{deptId}/{regNum}")
    public Boolean updDeptRegNumByDeptId(@PathVariable Long deptId, @PathVariable Integer regNum){
        return service.updDeptRegNumByDeptId(deptId,regNum);
    }

}
