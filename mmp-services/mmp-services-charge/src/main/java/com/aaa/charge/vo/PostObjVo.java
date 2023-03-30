package com.aaa.charge.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostObjVo {
//    //总金额
//    private Double orderAmount;
//    //病历ID
//    private String chId;
//    //患者名字
//    private String patientName;
//    //挂号单号
//    private String regId;
//    //开诊明细id
//    private String itemId;
//    //处方id
//    private String coId;
//    //药品名称
//    private String itemName;
//    //单价
//    private String itemPrice;
//    //数量
//    private String itemNum;
//    //项目药品检查项
//    private String itemType;
//    //金额
//    private String itemAmount;
    private List<PostObjVo1> orderChargeItemDtoList;
    private   PostObjVo2 orderChargeDto;
}
