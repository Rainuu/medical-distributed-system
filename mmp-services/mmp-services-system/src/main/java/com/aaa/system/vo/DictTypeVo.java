package com.aaa.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictTypeVo {

    private String dictName;

    private String dictType;

    private Long status;

    private String[] createTime;
}
