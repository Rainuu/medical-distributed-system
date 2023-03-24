package com.aaa.check.service;


import com.aaa.core.entity.CheckResult;
import com.aaa.core.vo.CheckItemVo;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * (HisCheckResult)表服务接口
 *
 * @author makejava
 * @since 2023-03-24 09:37:25
 */
public interface CheckResultService {
    Result<IPage<CheckResult>> getByPage(Integer current, Integer size, CheckItemVo checkItemVo);
}
