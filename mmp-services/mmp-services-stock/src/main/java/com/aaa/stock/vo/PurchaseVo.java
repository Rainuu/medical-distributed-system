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

    private String providerName;

    private String applyUserName;

    private Integer status;
}
