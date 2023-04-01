package com.aaa.core.vo;

import lombok.Data;

@Data
public class CheckResultVo {
    private String checkItemId;
    private String patientName;
    private String[] dateRange;
}
