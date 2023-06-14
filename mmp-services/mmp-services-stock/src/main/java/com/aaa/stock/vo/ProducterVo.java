package com.aaa.stock.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@Data
public class ProducterVo {
    /**
     * 厂家名称
     */
    private String producerName;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 厂家电话
     */
    private String producerTel;

    /**
     * 状态标志（0正常 1停用）sys_normal_disable
     * 前端需要的是数字，所以使用Integer或Long都可
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String[] dateRange;

}
