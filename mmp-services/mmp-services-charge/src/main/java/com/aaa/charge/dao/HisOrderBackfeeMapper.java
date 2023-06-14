package com.aaa.charge.dao;

import com.aaa.core.entity.OrderBackfee;
import com.aaa.core.entity.OrderBackfeeItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 退费主表(HisOrderBackfee)表数据库访问层
 */

public interface HisOrderBackfeeMapper extends BaseMapper<OrderBackfee> {

    ////查询所有OrderBackfeeItem的数据根据id返回
    List<OrderBackfeeItem> selectAll(String backId);

    void insertAll(String backId, String regId, String patientName, BigDecimal backAmount, LocalDateTime createTime, LocalDateTime backTime);

    void insertAllzfb(String backId, String regId, String patientName, BigDecimal backAmount, LocalDateTime createTime, LocalDateTime bcakTime);
}
