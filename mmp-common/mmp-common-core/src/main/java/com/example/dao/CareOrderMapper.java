package com.example.dao;

import com.example.model.CareOrder;

import java.util.List;

public interface CareOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CareOrder record);

    CareOrder selectByPrimaryKey(Long id);

    List<CareOrder> selectAll();

    int updateByPrimaryKey(CareOrder record);
}