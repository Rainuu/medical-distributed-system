package com.aaa.doctor.vo;

import lombok.Data;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/4/1 10:18
 */
@Data
public class NewCareVo {
    private Long pageNum;
    private Long pageSize;
    private String keywords;
}
