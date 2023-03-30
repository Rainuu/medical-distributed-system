package com.aaa.system.service.Impl;

import com.aaa.core.entity.LoginInfo;
import com.aaa.core.vo.Result;
import com.aaa.system.dao.LoginLogDao;
import com.aaa.system.service.LoginLogService;
import com.aaa.system.vo.LoginInfoVo;
import com.aaa.system.vo.OpenLogVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLogServiceImpl implements LoginLogService {
@Autowired
private LoginLogDao dao;
    @Override
    public int AopLoginLog(LoginInfo loginInfo) {
        return dao.insert(loginInfo);
    }
    @Override
    public Result getAll(Page page, LoginInfoVo loginInfoVo) {
        QueryWrapper queryWrapper=new QueryWrapper<>();
        if (loginInfoVo.getUserName()!=null){
            queryWrapper.like("user_name",loginInfoVo.getUserName());
        }
        if (loginInfoVo.getLoginAccount()!=null){
            queryWrapper.like("login_account",loginInfoVo.getLoginAccount());
        }
        if (loginInfoVo.getIpAddr()!=null){
            queryWrapper.like("ip_addr",loginInfoVo.getIpAddr());
        }
        if (loginInfoVo.getLoginStatus()!=null){
            queryWrapper.eq("login_status",loginInfoVo.getLoginStatus());
        }
        if (loginInfoVo.getLoginType()!=null){
            queryWrapper.eq("login_type",loginInfoVo.getLoginType());
        }
        if (loginInfoVo.getDateRange()!=null){
            queryWrapper.between("login_time",loginInfoVo.getDateRange()[0],loginInfoVo.getDateRange()[1]);
        }
        Page page1 = dao.selectPage(page, queryWrapper);
        return new Result(200,null,page1);
    }


    @Override
    public Result delLoginInfo(Long id) {
        int i = dao.deleteById(id);
        if (i>0){
            return new Result(200,null,true);
        }
        return new Result(200,null,false);
    }

    @Override
    public Result clean() {
        int delete = dao.delete(null);

        if (delete>0){
            return new Result(200,"删除成功",delete);
        }
        return new Result(200,null,false);
    }
}
