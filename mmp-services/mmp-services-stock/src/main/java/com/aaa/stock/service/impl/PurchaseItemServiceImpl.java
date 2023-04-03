package com.aaa.stock.service.impl;

import com.aaa.core.entity.Medicines;
import com.aaa.core.entity.Purchase;
import com.aaa.core.entity.PurchaseItem;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.PurchaseDao;
import com.aaa.stock.dao.PurchaseItemDao;
import com.aaa.stock.service.PurchaseItemService;
import com.aaa.stock.service.PurchaseService;
import com.aaa.stock.vo.PurchaseItemVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/4/3 8:00
 */
@Service
public class PurchaseItemServiceImpl extends ServiceImpl<PurchaseItemDao, PurchaseItem> implements PurchaseItemService {

    @Autowired
    private PurchaseItemDao purchaseItemDao;

    // 查看详情
    @Override
    public Result<List<PurchaseItem>> getPurchaseItemById(String purchaseId) {
        List<PurchaseItem> purchaseItemById = purchaseItemDao.getPurchaseItemById(purchaseId);
        return new Result<List<PurchaseItem>>(200,"查询成功",purchaseItemById);
    }
}
