package com.aaa.check.vo;

import lombok.Data;

@Data
public class CheckResultVo {
    private String checkItemName;
    private String patientName;
    private String[] dateRange;
}
