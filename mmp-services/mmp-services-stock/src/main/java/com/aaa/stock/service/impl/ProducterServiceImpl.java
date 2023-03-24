package com.aaa.stock.service.impl;

import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.ProducterDao;
import com.aaa.stock.service.ProducterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/24 10:55
 */
@Service
public class ProducterServiceImpl implements ProducterService {

    @Autowired
    private ProducterDao producterDao;

    // 根据ID查询厂家信息
    @Override
    public Result<List<Producer>> getAll() {
        List<Producer> producers = producterDao.getAll();
        return new Result(2000, "查询查询厂家信息", producers);
    }
}
