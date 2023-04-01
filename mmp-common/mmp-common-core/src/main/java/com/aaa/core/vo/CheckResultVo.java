package com.aaa.core.vo;

import lombok.Data;

@Data
public class CheckResultVo {
    private String checkItemName;
    private String patientName;
    private String[] dateRange;
}
