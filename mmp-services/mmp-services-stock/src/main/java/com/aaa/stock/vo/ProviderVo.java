package com.aaa.stock.vo;

import lombok.Data;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION: 接收前端用于查询的请求数据，封装前端用于模糊查询的字段,返回值类型要和前端对照
 * @USER: HZM
 * @DATE: 2023/3/26 16:20
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
