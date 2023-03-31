package com.aaa.check.dao;

import com.aaa.check.vo.CheckResultVo;
import com.aaa.core.entity.CheckResult;
import com.aaa.core.vo.CheckItemVo;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CheckResultDao extends BaseMapper<CheckResult> {


    Boolean addMsg(String codId,String textarea,String url);

    IPage<CheckResult> selectAll(IPage<CheckResult> page,CheckItemVo checkItemVo);

    IPage<CheckResult> selectAllTwo(IPage page, CheckResultVo checkResultVo);
}
