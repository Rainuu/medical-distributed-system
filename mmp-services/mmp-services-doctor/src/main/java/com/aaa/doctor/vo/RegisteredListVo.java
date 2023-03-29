package com.aaa.doctor.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/27 11:47
 */
@Data
public class RegisteredListVo {
//    /*
//    * 部门
//    * */
//    private Long deptId;
//
//    /*
//     * 患者名称
//     * */
//    private String patientName;
//
//    /*
//     * 挂号类型
//     * */
//    private String schedulingType;
//
//    /*
//     * 挂号时段
//     * */
//    private String subsectionType;
//
//    /*
//     * 挂号状态
//     * */
//    private String registrationStatus;
//
//    /*
//    * 挂号时间
//    * */
//    private String createTime;

    private Long deptId;
    private String patientName;
    private Long type;
    private Long timeInterval;
    private Long status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8" )
    private Date time;
}
