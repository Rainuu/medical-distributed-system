package com.aaa.doctor.vo;

import lombok.Data;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/30 13:23
 */
@Data
public class SchedulingVoo {
    //部门编号
    private Long deptId;
    //挂号类型
    private String SchedulingType;
    //挂号时段
    private String subsectionType;
    //挂号时间(值班日期)
    //@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //@DataTimeFormat(pattern = "yyyy-MM-dd")
    //@DataFormat(pattern = "yyyy-MM-dd")
    private String schedulingDay;
}
