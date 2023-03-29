package com.aaa.doctor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/29 9:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingVo {
    private Long userId;
    private Long deptId;
    private String queryDate;// 查询时间
    private String beginDate;// 这周开始时间
    private String endDate;// 这周结束时间

}
