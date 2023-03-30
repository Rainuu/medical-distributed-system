package com.aaa.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfoVo {
    private String userName;
    private String loginAccount;
    private String ipAddr;
    private String loginStatus;
    private String loginType;
    private String[] dateRange;
}
