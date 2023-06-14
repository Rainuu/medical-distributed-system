package com.aaa.stock.service.impl;

import com.aaa.core.entity.PurchaseItem;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.PurchaseItemDao;
import com.aaa.stock.service.PurchaseItemService;
import com.aaa.stock.vo.editPurchaseVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@Service
public class PurchaseItemServiceImpl extends ServiceImpl<PurchaseItemDao, PurchaseItem> implements PurchaseItemService {

    @Autowired
    private PurchaseItemDao purchaseItemDao;

    // 查看详情
    @Override
    public Result<List<PurchaseItem>> getPurchaseItemById(String purchaseId) {
        List<PurchaseItem> purchaseItemById = purchaseItemDao.getPurchaseItemById(purchaseId);
        return new Result<List<PurchaseItem>>(200, "查询成功", purchaseItemById);
    }

    @Override
    public Result<List<editPurchaseVo>> getAllById(String purchaseId) {
        List<editPurchaseVo> allById = purchaseItemDao.getAllById(purchaseId);
        return new Result(200, "成功", allById);
    }

}
