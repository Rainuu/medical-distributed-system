package com.aaa.stock.vo;

import lombok.Data;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@Data
public class PurchaseAllVo {

    private PurchaseDtoVo purchaseDto;

    private List<PurchaseItemDtosVo> purchaseItemDtos;

}
