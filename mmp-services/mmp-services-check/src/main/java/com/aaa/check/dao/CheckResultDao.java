package com.aaa.check.dao;

import com.aaa.core.vo.CheckResultVo;
import com.aaa.core.entity.CheckResult;
import com.aaa.core.vo.CheckItemVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface CheckResultDao extends BaseMapper<CheckResult> {


    Boolean addMsg(String codId,String textarea,String url);

    IPage<CheckResult> selectAll(IPage<CheckResult> page,CheckItemVo checkItemVo);

    List<CheckResult> selectAllTwo(String checkItemId,String[] dateRange);
}
