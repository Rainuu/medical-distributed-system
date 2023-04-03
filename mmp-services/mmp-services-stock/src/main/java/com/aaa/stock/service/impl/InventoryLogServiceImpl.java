package com.aaa.stock.service.impl;

import com.aaa.core.entity.InventoryLog;
import com.aaa.core.entity.PurchaseItem;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.InventoryLogDao;
import com.aaa.stock.dao.PurchaseItemDao;
import com.aaa.stock.service.InventoryLogService;
import com.aaa.stock.service.PurchaseItemService;
import com.aaa.stock.vo.InventoryLogVo;
import com.aaa.stock.vo.PurchaseItemVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/4/3 9:17
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

        IPage<InventoryLog> page1 = inventoryLogDao.selectPage(page, wrapper);
        return new Result(200, "成功", page1);
    }

}
