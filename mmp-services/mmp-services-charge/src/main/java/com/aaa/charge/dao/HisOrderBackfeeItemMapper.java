package com.aaa.charge.dao;

import com.aaa.core.entity.OrderBackfeeItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 退费主表(HisOrderBackfee)表数据库访问层
 */

public interface HisOrderBackfeeItemMapper extends BaseMapper<OrderBackfeeItem> {


    void updateBystatus(String itemId);
}
