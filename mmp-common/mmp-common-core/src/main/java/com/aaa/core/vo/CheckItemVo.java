package com.aaa.core.vo;

import lombok.Data;

@Data
public class CheckItemVo {
    private String[] checkItemIds;
    private String regId;
    private String patientName;
    private String status;

}
