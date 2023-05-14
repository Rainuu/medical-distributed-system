package com.aaa.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenLogVo {
    private Spring title;
    private String operName;
    private String businessType;
    private Long status;
    private String[] dateRange;
}
