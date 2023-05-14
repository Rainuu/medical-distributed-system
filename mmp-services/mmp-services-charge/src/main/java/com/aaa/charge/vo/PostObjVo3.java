package com.aaa.charge.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PostObjVo3 {
    //总金额
    private BigDecimal backAmount;
    //病历ID
    private String chId;
    //患者名字
    private String patientName;
    //挂号单号
    private String regId;
}
