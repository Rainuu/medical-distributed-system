package com.aaa.stock.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/4/3 21:28
 */
@Data
public class purchaseDtoVo {
    //制单号ID 全局ID雪花算法
    private String purchaseId;
    //供应商ID
    private String providerId;
    //采购批发总额
    private BigDecimal purchaseTradeTotalAmount;
}
