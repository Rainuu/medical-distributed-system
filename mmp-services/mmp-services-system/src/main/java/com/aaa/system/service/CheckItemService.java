package com.aaa.system.service;

import com.aaa.core.entity.CheckItem;
import com.aaa.core.vo.Result;
import com.aaa.system.vo.CheckItemVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface CheckItemService {

    Result getAll(Page page, CheckItemVo checkItemVo);
    Result saveAndUp(CheckItem checkItem);
    Result delInfo(Long id);
}
