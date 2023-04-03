package com.aaa.system.controller;

import com.aaa.core.entity.Dept;
import com.aaa.core.entity.User;
import com.aaa.system.service.DeptService;
import com.aaa.system.service.UserSercvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserSercvice userSercvice;

    @Autowired
    private DeptService deptService;

    @GetMapping("/getByUsername/{username}")
    public User getByUsername(@PathVariable String username){
        return userSercvice.findByUsername(username);
    }

    @PostMapping("/queryUsersNeedScheduling")
    public List<User> queryUsersNeedScheduling(@RequestBody Map<String,Object> map){
        return userSercvice.queryUserScheduling(map);
    }

    @GetMapping("{id}")
    public User queryById(@PathVariable Long id){

      return userSercvice.queryById(id);
    }



    /**
     * 通过userId获取User
     * @param userId
     * @return
     */
    @PostMapping("getUserByUserId/{userId}")
    public User getUserByUserId(@PathVariable Long userId){
        return userSercvice.getUserByUserId(userId);
    }


    /**
     * 通过deptId获取Dept
     * @param deptId
     * @return
     */
    @GetMapping("getDeptByDeptId/{deptId}")
    public Dept getDeptByDeptId(@PathVariable Long deptId){
        return deptService.getDeptByDeptId(deptId);
    }

}
