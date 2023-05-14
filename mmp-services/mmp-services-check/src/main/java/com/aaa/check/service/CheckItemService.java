package com.aaa.check.service;


import com.aaa.check.vo.NewCareVo;
import com.aaa.core.entity.CheckItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 检查费用表(SysCheckItem)表服务接口
 *
 * @author makejava
 * @since 2023-03-24 17:32:14
 */
public interface CheckItemService {


    Result<List<CheckItem>> selectAllCheckItem();

    Page<CheckItem> selectByKey(NewCareVo newCareVo);
}
