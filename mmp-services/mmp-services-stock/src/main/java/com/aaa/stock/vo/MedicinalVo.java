package com.aaa.stock.vo;

import lombok.Data;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/27 16:04
 */
@Data
public class MedicinalVo {
    private Long medicinesId;
    /**
     * 药品名称
     */
    private String medicinesName;
    /**
     * 关键字
     */
    private String keywords;
    /**
     * 药品分类 sys_dict_data表his_medicines_type
     */
    private String medicinesType;
    /**
     * 厂家名称 (stock_producer表的字段)
     */
    private String producerName;
    /**
     * 处方类型 sys_dict_data表his_prescription_type
     */
    private String prescriptionType;
    /**
     * 药品状态0正常0停用 sys_dict_data表 sys_normal_disable
     */
    private Integer status;


}
