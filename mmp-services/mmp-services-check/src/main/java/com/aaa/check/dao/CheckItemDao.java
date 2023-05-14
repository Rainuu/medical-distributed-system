package com.aaa.check.dao;

import com.aaa.core.entity.CheckItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * 检查费用表(SysCheckItem)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-24 17:32:14
 */

public interface CheckItemDao extends BaseMapper<CheckItem> {

    List<String> selectAllCheckItem();
}
