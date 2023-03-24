package com.aaa.system.service;

import com.aaa.core.entity.DictData;
import com.aaa.core.vo.Result;

import java.util.List;

public interface DictDataService {
    List<DictData> findByType(String type);

    List<DictData> getall();
}
