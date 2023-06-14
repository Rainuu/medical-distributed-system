package com.aaa.stock.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@Data
public class PurchaseDtoVo {
    //制单号ID 全局ID雪花算法
    private String purchaseId;
    //供应商ID
    private String providerId;
    //采购批发总额
    private BigDecimal purchaseTradeTotalAmount;
}
