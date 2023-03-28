package com.aaa.stock.service;

import com.aaa.core.entity.DictData;
import com.aaa.core.entity.Producer;
import com.aaa.core.entity.User;
import com.aaa.core.vo.Result;
import com.aaa.stock.vo.ProducterVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/24 10:45
 */
public interface ProducterService extends IService<Producer> {
    //查询页面所有数据，并实现分页
    Result<IPage<Producer>> getAll(Integer current, Integer size, ProducterVo producterVo);

    boolean delById(Long id);

    boolean saveAndUpdate(Producer producer);

}
