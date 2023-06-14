package com.aaa.stock.service;

import com.aaa.core.entity.InventoryLog;
import com.aaa.core.vo.Result;
import com.aaa.stock.vo.InventoryLogVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
public interface InventoryLogService extends IService<InventoryLog> {

    //全查+分页+模糊
    Result<IPage<InventoryLog>> getAll(Integer current2, Integer size2, InventoryLogVo inventoryLogVo);

}
