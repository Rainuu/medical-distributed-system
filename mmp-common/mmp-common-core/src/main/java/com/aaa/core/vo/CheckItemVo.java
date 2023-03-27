package com.aaa.core.vo;

import lombok.Data;

import java.util.List;

@Data
public class CheckItemVo {
    private List checkItemIds;
    private String regId;
    private String patientName;
    private Integer status;

}
