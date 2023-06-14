package com.aaa.stock.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
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

    private Integer producerId;

    private Integer producterId;

    // 当前页
    private Integer current;

    // 显示条数
    private Integer size;

    // 总条数
    private Integer total;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String[] dateRange;

}
