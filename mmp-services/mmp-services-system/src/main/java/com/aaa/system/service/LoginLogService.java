package com.aaa.system.service;

import com.aaa.core.entity.LoginInfo;
import com.aaa.core.vo.Result;
import com.aaa.system.vo.LoginInfoVo;
import com.aaa.system.vo.OpenLogVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface LoginLogService {
    int AopLoginLog(LoginInfo loginInfo);

    Result getAll(Page page, LoginInfoVo loginInfoVo);

    Result delLoginInfo(Long id);

    Result clean();
}
