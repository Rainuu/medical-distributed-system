package com.aaa.system.service;

import com.aaa.core.entity.DictData;
import com.aaa.core.vo.Result;

import java.util.List;

public interface DictDataService {
    Result<List<DictData>> findByType(String type);

    Result<List<DictData>> getall();
}
