package com.aaa.check.dao;

import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.CheckItemVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface CareOrderItemDao extends BaseMapper<CareOrderItem> {
    List<CareOrderItem> selectAll(List checkItemIds,String regId);
}
