package com.aaa.check.dao;

import com.aaa.core.entity.CheckResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CheckResultDao extends BaseMapper<CheckResult> {

    @Select("select * from his_check_result")
    List<CheckResult> select();
}
