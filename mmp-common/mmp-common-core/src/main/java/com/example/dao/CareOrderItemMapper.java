package com.example.dao;

import com.example.model.CareOrderItem;

import java.util.List;

public interface CareOrderItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CareOrderItem record);

    CareOrderItem selectByPrimaryKey(Long id);

    List<CareOrderItem> selectAll();

    int updateByPrimaryKey(CareOrderItem record);
}