package com.aaa.stock.service;

import com.aaa.core.entity.Purchase;
import com.aaa.core.entity.PurchaseItem;
import com.aaa.core.vo.Result;
import com.aaa.stock.vo.PurchaseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/28 19:52
 */
public interface PurchaseService extends IService<Purchase> {
    //查询页面所有数据，并实现分页
    Result<IPage<Purchase>> getAll(Integer current, Integer size, PurchaseVo purchaseVo);

    Result<IPage<Purchase>> listPurchasePendingForPage(Integer current, Integer size, PurchaseVo purchaseVo);

    void auditPass(String purchaseId);

    void auditNoPass(String purchaseId, String value);

    Result<List<PurchaseItem>> getPurchaseItemById(String purchaseId);
}
