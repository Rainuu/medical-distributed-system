package com.aaa.charge.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderChargeVo {
    //患者名字
    private String patientName;
    //挂号单
    private String regId;
    //订单号
    private String orderId;
    //总价
    private BigDecimal orderAmount;
    //订单名字
    private String itemName;


}
