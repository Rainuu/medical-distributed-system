package com.aaa.system.vo;


import lombok.Data;

@Data
//接收前端请求数据
public class UserVo {
    private Long deptId;
    private String userName;
    private String phone;
    private Integer status;
    private String[] dateRange;

}
