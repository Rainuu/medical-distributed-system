package com.aaa.check.dao;

import com.aaa.core.entity.CheckResult;
import com.aaa.core.vo.CheckItemVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CheckResultDao extends BaseMapper<CheckResult> {

    @Update(value = "update his_check_result set result_msg = #{textarea},result_status = 1 where coc_id = #{codId}")
    Boolean addMsg(String codId, String textarea);

    IPage<CheckResult> selectAll(IPage<CheckResult> page,CheckItemVo checkItemVo);
}
