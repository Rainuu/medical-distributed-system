package com.aaa.system.controller.api;

import com.aaa.core.entity.User;
import com.aaa.core.vo.Result;
import com.aaa.system.service.UserSercvice;
import com.aaa.system.vo.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system/api/user")
public class ApiUserController {
    @Autowired
    private UserSercvice userSercvice;
    @GetMapping("/getInfo")
    public Result<User> getInfo(){

        return userSercvice.getByPhoneInfo();
    }
    @PostMapping("searchUser/{curr}/{size}")
    public Result<IPage<User>> searchuser(
            @PathVariable Integer curr,
            @PathVariable Integer size,
            @RequestBody UserVo userVo
    ){
        return userSercvice.findByCoodtion(curr,size,userVo);
    }
    @DeleteMapping("{id}")
    public Result deleById(@PathVariable Long id){
        if (userSercvice.deleById(id)){
            return new Result(200,"删除成功",true);
        }else {
            return new Result(500,"删除失败",false);
        }
    }
    @PostMapping("saveandsupdate")
    public Result saveAndUpdate(User user){
        if (userSercvice.saveAndUp(user)){
            return new Result(200,"删除成功",true);
        }else {
            return new Result(500,"删除失败",false);
        }
    }
}
