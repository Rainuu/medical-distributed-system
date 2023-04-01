package com.aaa.statistics.service;

import com.aaa.core.entity.CheckResult;
import com.aaa.core.vo.CheckResultVo;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface CheckService {

    Result<List<CheckResult>> selectAll(CheckResultVo checkResultVo);

    Result<List<CheckResult>> selectAllTwo(CheckResultVo checkResultVo);
}
