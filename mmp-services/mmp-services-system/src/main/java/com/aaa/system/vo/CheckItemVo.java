package com.aaa.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckItemVo {
    private String checkItemName;
    private String keywords;
    private String typeId;
    private String status;
}
