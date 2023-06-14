package com.aaa.stock.service.impl;

import com.aaa.core.entity.InventoryLog;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.InventoryLogDao;
import com.aaa.stock.service.InventoryLogService;
import com.aaa.stock.vo.InventoryLogVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@Service
public class InventoryLogServiceImpl extends ServiceImpl<InventoryLogDao, InventoryLog> implements InventoryLogService {

    @Autowired
    private InventoryLogDao inventoryLogDao;

    // 查询 & 分页 & 模糊查询
    @Override
    public Result<IPage<InventoryLog>> getAll(Integer current2, Integer size2, InventoryLogVo inventoryLogVo) {
        IPage<InventoryLog> page = new Page(current2, size2);
        QueryWrapper<InventoryLog> wrapper = new QueryWrapper();
        if (StringUtils.hasText(inventoryLogVo.getPurchaseId())) {
            wrapper.eq("purchase_id", inventoryLogVo.getPurchaseId());
        }
        if (StringUtils.hasText(inventoryLogVo.getMedicinesName())) {
            wrapper.like("medicines_name", inventoryLogVo.getMedicinesName());
        }
        if (Objects.nonNull(inventoryLogVo.getMedicinesType())) {
            wrapper.like("medicines_type", inventoryLogVo.getMedicinesType());
        }
        if (Objects.nonNull(inventoryLogVo.getPrescriptionType())) {
            wrapper.like("prescription_type", inventoryLogVo.getPrescriptionType());
        }
        if (Objects.nonNull(inventoryLogVo.getProducterId())) {
            wrapper.eq("producter_id", inventoryLogVo.getProducterId());
        }
        if (Objects.nonNull(inventoryLogVo.getPrescriptionType())) {
            wrapper.like("prescription_type", inventoryLogVo.getPrescriptionType());
        }
        if (Objects.nonNull(inventoryLogVo.getDateRange()) && inventoryLogVo.getDateRange().length == 2) {
            wrapper.between("create_time", inventoryLogVo.getDateRange()[0], inventoryLogVo.getDateRange()[1]);
        }
        IPage<InventoryLog> page1 = inventoryLogDao.selectPage(page, wrapper);
        return new Result(200, "成功", page1);
    }

}
