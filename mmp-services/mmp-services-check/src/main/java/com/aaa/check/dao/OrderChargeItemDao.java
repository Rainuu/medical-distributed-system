package com.aaa.check.dao;

import com.aaa.core.entity.OrderChargeItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

public interface OrderChargeItemDao extends BaseMapper<OrderChargeItem> {
    @Update(value = "update his_order_charge_item set status=3 where item_id = #{itemId}")
    Boolean updateStatus(String itemId);
}
