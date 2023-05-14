package com.aaa.stock.service;

import com.aaa.core.entity.PurchaseItem;
import com.aaa.core.vo.Result;
import com.aaa.stock.vo.editPurchaseVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/4/3 7:58
 */
public interface PurchaseItemService extends IService<PurchaseItem> {

    // 查看详情
    Result<List<PurchaseItem>> getPurchaseItemById(String purchaseId);


    Result<List<editPurchaseVo>> getAllById(String purchaseId);
}
