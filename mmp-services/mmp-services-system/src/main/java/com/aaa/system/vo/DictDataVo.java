package com.aaa.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lml
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictDataVo {
    private String dictId;
   private String dictLabel;
    private String dictType;
    private String status;
}
