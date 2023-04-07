package com.aaa.stock.service;

import com.aaa.core.entity.Purchase;
import com.aaa.core.entity.PurchaseItem;
import com.aaa.core.vo.Result;
import com.aaa.stock.vo.PurchaseVo;
import com.aaa.stock.vo.PurchaseAllVo;
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
    // 审核通过 status=3
    void auditPass(String purchaseId);
    // 审核不通过 status=4
    void auditNoPass(String purchaseId, String value);

    void insertByPurchaseId(Purchase purchase);

    Result<List<PurchaseItem>> addPurchase(PurchaseAllVo purchaseAllVo);

    Result<List<PurchaseItem>> addPurchaseToAudit(PurchaseAllVo purchaseAllVo);

    Result<List<PurchaseItem>> editPurchase(PurchaseAllVo purchaseAllVo);

    Result<List<PurchaseItem>> editPurchaseToAudit(PurchaseAllVo purchaseAllVo);

    void doInvalid(String purchaseId);

    void doAudit(String purchaseId);

    void doInventory(String purchaseId);

}
