package com.aaa.stock.dao;

import com.aaa.core.entity.Purchase;
import com.aaa.stock.vo.PurchaseVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/28 19:51
 */
@Mapper
public interface PurchaseDao extends BaseMapper<Purchase> {

    IPage<Purchase> listPurchasePendingForPage(IPage<Purchase> page, @Param("purchaseVo") PurchaseVo purchaseVo);

    void auditPass(String purchaseId);

    void auditNoPass(String purchaseId, String value);

    void insertByPurchaseId(@Param("purchase") Purchase purchase);

    int updateByPurchaseId(String purchaseId);

    void doAudit(String purchaseId);

    void RuKuUpd(String purchaseId);

    void updateStatusTo1(@Param("purchaseId") String purchaseId);

    void updateStatusTo2(@Param("purchaseId") String purchaseId);
}
