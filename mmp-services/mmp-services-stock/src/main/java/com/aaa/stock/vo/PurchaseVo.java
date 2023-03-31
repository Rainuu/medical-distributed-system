package com.aaa.stock.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/28 20:51
 */
@Data
public class PurchaseVo {
    /**
     * 供应商ID
     */
    private Integer providerId;
    /**
     * 申请人ID
     */
    private Long applyUserId;
    /**
     * 单据状态； 1未提交2待审核 3审核通过 4审核失败 5作废 6入库成功 字典表 his_order_status
     */
    private Integer status;

}
