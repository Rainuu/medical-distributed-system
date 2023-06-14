package com.aaa.stock.service;

import com.aaa.core.entity.DictData;
import com.aaa.core.vo.Result;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
public interface FeignService {

    Result<List<DictData>> getOpenFeign(); //处理字典

}
