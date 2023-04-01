package com.aaa.statistics.service.Impl;

import com.aaa.core.entity.CheckResult;
import com.aaa.core.vo.CheckResultVo;
import com.aaa.core.vo.Result;
import com.aaa.statistics.feign.CheckFeign;
import com.aaa.statistics.service.CheckService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckFeign checkFeign;

    @Override
    public Result<List<CheckResult>> selectAll(CheckResultVo checkResultVo) {
        return checkFeign.selectAll(checkResultVo);
    }

    @Override
    public Result<List<CheckResult>> selectAllTwo(CheckResultVo checkResultVo) {
        return checkFeign.selectAllTwo(checkResultVo);
    }
}
