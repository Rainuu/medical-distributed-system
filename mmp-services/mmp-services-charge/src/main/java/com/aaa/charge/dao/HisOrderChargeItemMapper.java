package com.aaa.charge.dao;

import com.aaa.core.entity.OrderChargeItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * 收费表(HisOrderCharge)表数据库访问层
 */

public interface HisOrderChargeItemMapper extends BaseMapper<OrderChargeItem> {

    //查询所有OrderChargeItem的数据根据id返回
    List<OrderChargeItem> selectListAll(String orderId);


    void updByDispense(String itemId);

    void updataBystatus(String orderId);

//    List<OrderChargeItem> selectList(String orderId);
}