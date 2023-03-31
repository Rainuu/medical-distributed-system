package com.aaa.charge.dao;
import com.aaa.core.entity.OrderBackfee;
import com.aaa.core.entity.OrderBackfeeItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 退费主表(HisOrderBackfee)表数据库访问层
 * 
 * @author makejava
 * @since 2023-03-25 11:09:15
 */

public interface HisOrderBackfeeItemMapper extends BaseMapper<OrderBackfeeItem> {


    void updateBystatus(String itemId);
}
