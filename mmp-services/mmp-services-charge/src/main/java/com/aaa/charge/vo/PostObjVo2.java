package com.aaa.charge.vo;

import lombok.Data;

@Data
public class PostObjVo2 {
    //总金额
    private Double orderAmount;
    //病历ID
    private String chId;
    //患者名字
    private String patientName;
    //挂号单号
    private String regId;
}
