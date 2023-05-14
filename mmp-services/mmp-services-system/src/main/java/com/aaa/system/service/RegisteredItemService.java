package com.aaa.system.service;

import com.aaa.core.entity.RegisteredItem;
import com.aaa.core.vo.Result;
import com.aaa.system.vo.RegisteredItemVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface RegisteredItemService {

    Result getAll(Page page, RegisteredItemVo registeredItemVo);

    Result saveAndUp(RegisteredItem registeredItem);

    Result delInfo(Long id);
}
