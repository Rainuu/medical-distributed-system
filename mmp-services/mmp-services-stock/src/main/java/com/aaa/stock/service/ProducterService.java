package com.aaa.stock.service;

import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/24 10:45
 */
public interface ProducterService {

    // 根据ID查询厂家信息
    Result<List<Producer>> getAll();


}
