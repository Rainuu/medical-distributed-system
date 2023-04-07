package com.aaa.check.dao;

import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.CheckItemVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CareOrderItemDao extends BaseMapper<CareOrderItem> {
    List<CareOrderItem> selectAll(List checkItemIds,String regId);

    @Update(value = "update his_care_order_item set status=3 where item_id = #{itemId}")
    Boolean updateStatus(String itemId);
}
