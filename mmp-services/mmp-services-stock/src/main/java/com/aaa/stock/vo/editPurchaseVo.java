package com.aaa.stock.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/4/4 11:36
 */
@Data
public class editPurchaseVo {

    /**
     * 采购单据ID
     */
    private String purchaseId;

    /**
     * 供应商ID
     */
    private String providerId;

    /**
     * 采购批发总额
     */
    private BigDecimal purchaseTradeTotalAmount;

    /**
     * 单据状态； 1未提交2待审核 3审核通过 4审核失败 5作废 6入库成功 字典表 his_order_status
     */
    private String status;

    /**
     * 申请人ID
     */
    private Long applyUserId;

    /**
     * 申请人名称
     */
    private String applyUserName;

    /**
     * 入库操作人
     */
    private String storageOptUser;

    /**
     * 入库操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date storageOptTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 审核信息
     */
    private String examine;

    /**
     * 审核信息
     */
    private String auditMsg;


    private String itemId;

    /**
     * 药品ID
     */
    private String medicinesId;

    /**
     * 采购数量
     */
    private Integer purchaseNumber;

    /**
     * 批发价
     */
    private BigDecimal tradePrice;

    /**
     * 批发额
     */
    private BigDecimal tradeTotalAmount;

    /**
     * 药品生产批次号
     */
    private String batchNumber;

    /**
     * 备注
     */
    private String remark;

    /**
     * 药品名称
     */
    private String medicinesName;

    /**
     * 药品分类 sys_dict_data表his_medicines_type
     */
    private String medicinesType;

    /**
     * 处方类型 sys_dict_data表his_prescription_type
     */
    private String prescriptionType;

    /**
     * 生产厂家ID
     */
    private String producterId;

    private String producerId;
    /**
     * 换算量
     */
    private Integer conversion;

    /**
     * 单位（g/条）
     */
    private String unit;

    /**
     * 关键字
     */
    private String keywords;
}
