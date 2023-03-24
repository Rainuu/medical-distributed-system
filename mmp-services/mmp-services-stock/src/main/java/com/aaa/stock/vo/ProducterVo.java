package com.aaa.stock.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION: 用于模糊查询的数据，内部放的是需要模糊的字段
 * @USER: HZM
 * @DATE: 2023/3/24 14:56
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
     */
    private String status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String[] createTime;

}
