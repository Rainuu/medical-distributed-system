package com.aaa.stock.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@Data
public class InventoryLogVo {

    /**
     * 采购单据ID
     */
    private String purchaseId;
    /**
     * 药品ID
     */
    private String medicinesId;
    /**
     * 药品名称
     */
    private String medicinesName;
    /**
     * 药品分类 sys_dict_data表his_medicines_type
     */
    private String medicinesType;
    /**
     * 生产厂家ID
     */
    private String producterId;
    /**
     * 处方类型 sys_dict_data表his_prescription_type
     */
    private String prescriptionType;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String[] dateRange;
}
