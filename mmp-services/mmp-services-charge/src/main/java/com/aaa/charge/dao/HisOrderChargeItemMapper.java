package com.aaa.charge.dao;

import com.aaa.core.entity.OrderCharge;
import com.aaa.core.entity.OrderChargeItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * 收费表(HisOrderCharge)表数据库访问层
 * 
 * @author makejava
 * @since 2023-03-24 09:56:27
 */

public interface HisOrderChargeItemMapper extends BaseMapper<OrderChargeItem> {


    List<OrderChargeItem> selectListAll(String orderId);
}