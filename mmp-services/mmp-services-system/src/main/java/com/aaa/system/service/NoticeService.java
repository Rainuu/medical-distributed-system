package com.aaa.system.service;

import com.aaa.core.entity.Notice;
import com.aaa.core.vo.Result;
import com.aaa.system.vo.NoticeVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface NoticeService {

    Result getAll(Page page, NoticeVo noticeVo);

    Result saveAndUp(Notice notice);

    Result delById(Long id);
}
