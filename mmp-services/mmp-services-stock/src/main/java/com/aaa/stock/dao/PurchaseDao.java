package com.aaa.stock.dao;

import com.aaa.core.entity.Purchase;
import com.aaa.core.entity.PurchaseItem;
import com.aaa.stock.vo.PurchaseVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

}
