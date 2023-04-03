package com.aaa.stock.vo;

import lombok.Data;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/4/3 21:45
 */
@Data
public class purchaseAllVo {

    private purchaseDtoVo purchaseDto;

    private List<purchaseItemDtosVo> purchaseItemDtos;

}
