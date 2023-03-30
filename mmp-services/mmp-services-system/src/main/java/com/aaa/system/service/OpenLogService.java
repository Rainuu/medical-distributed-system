package com.aaa.system.service;

import com.aaa.core.entity.OperLog;
import com.aaa.core.vo.Result;
import com.aaa.system.vo.OpenLogVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface OpenLogService {

    Result getAll(Page page, OpenLogVo openLogVo);

    Result delOperLog(Long id);

    Result clean();
}
