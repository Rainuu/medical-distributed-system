package com.aaa.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo {

        private String roleName;
        private String roleCode;
        private Integer status;
        private String[] dateRange;

}
