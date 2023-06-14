package com.aaa.stock.vo;

import lombok.Data;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@Data
public class ProviderVo {
    /**
     * 供应商名称
     */
    private String providerName;
    /**
     * 联系人名称
     */
    private String contactName;
    /**
     * 联系人电话
     */
    private String contactTel;
    /**
     * 状态（0正常 1停用）sys_normal_disable
     */
    private Integer status;

}
