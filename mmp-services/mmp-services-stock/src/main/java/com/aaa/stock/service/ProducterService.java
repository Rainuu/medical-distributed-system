package com.aaa.stock.service;

import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;
import com.aaa.stock.vo.ProducterVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
public interface ProducterService extends IService<Producer> {

    // 查询 & 分页 & 模糊
    Result<IPage<Producer>> getAll(Integer current, Integer size, ProducterVo producterVo);

    // 修改 & 新增
    boolean saveAndUpdate(Producer producer);

    // 删除
    boolean delById(Long id);
}
