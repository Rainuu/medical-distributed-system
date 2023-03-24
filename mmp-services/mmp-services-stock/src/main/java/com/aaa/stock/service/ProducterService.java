package com.aaa.stock.service;

import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;
import com.aaa.stock.vo.ProducterVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/24 10:45
 */
public interface ProducterService {

    Result<IPage<Producer>> getAll(Integer current, Integer size, ProducterVo producterVo);
}
