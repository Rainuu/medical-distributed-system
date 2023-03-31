package com.aaa.charge.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PostObjVo1 {
    //开诊明细id
    private String itemId;
    //处方id
    private String coId;
    //药品名称
    private String itemName;
    //单价
    private BigDecimal itemPrice;
    //数量
    private Integer itemNum;
    //项目药品检查项
    private String itemType;
    //金额
    private Integer itemAmount;
}
