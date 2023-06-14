package com.aaa.stock.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@Data
public class PurchaseVo {

    // （stock_purchase）form的数据
    private String purchaseId;  // 制单号ID 全局ID雪花算法

    private Integer providerId;  // 供应商ID

    private BigDecimal purchaseTradeTotalAmount; // 采购批发总额

    private Integer status; // 单据状态； 1未提交2待审核 3审核通过 4审核失败 5作废 6入库成功 字典表 his_order_status

    private Long applyUserId; // 申请人ID

    private String applyUserName; // 申请人名称

    // （stock_medicines）purchaseItemList的数据

    private Long medicinesId; // 药品ID

    private String medicinesNumber; // 药品编号

    private String medicinesName; // 药品名称

    private String medicinesType; // 药品分类 sys_dict_data表his_medicines_type

    private String prescriptionType; // 处方类型 sys_dict_data表his_prescription_type

    private BigDecimal prescriptionPrice; // 处方价格

    private String unit; // 单位（g/条）

    private Integer conversion; // 换算量

    private String keywords; // 关键字

    private String producterId; // 生产厂家ID

    private Integer medicinesStockNum; // 库存量

    private Integer medicinesStockDangerNum; // 预警值

    private Date createTime; // 创建时间

    private Date updateTime; // 更新时间

    private String createBy; // 创建者

    private String updateBy; // 更新者

    private String delFlag; // 删除状态0正常0删除 要有重新导入功能

}
