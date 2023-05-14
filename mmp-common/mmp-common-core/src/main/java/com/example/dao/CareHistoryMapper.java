package com.example.dao;

import com.example.model.CareHistory;

import java.util.List;

public interface CareHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CareHistory record);

    List<CareHistory> selectAll();
}