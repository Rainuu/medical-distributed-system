package com.aaa.stock.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@Data
public class PurchaseItemDtosVo {

    // stock_medicines表

    // 药品id
    private Long medicinesId;
    // 药品编号
    private String medicinesNumber;
    // 药品名称
    private String medicinesName;
    // 药品分类 sys_dict_data表his_medicines_type
    private String medicinesType;
    // 处方类型 sys_dict_data表his_prescription_type
    private String prescriptionType;
    // 处方价格
    private BigDecimal prescriptionPrice;
    // 单位（g/条）
    private String unit;
    // 换算量
    private Integer conversion;
    // 关键字
    private String keywords;
    // 生产厂家ID
    private String producterId;
    // 删除状态0正常0删除 要有重新导入功能
    private String delFlag;

    // stock_purchase_item表

    // 采购数量
    private Integer purchaseNumber;
    // 批发价
    private BigDecimal tradePrice;
    // 批发额
    private BigDecimal tradeTotalAmount;
    // 药品生产批次号
    private String batchNumber;
    // 备注
    private String remark;
}
