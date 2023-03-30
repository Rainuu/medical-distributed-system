package com.aaa.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVo {

    private String noticeTitle;
    private String createBy;
    private Long noticeType;
    private Long status;
}
