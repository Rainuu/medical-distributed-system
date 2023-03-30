package com.aaa.charge.dao;
import com.aaa.core.entity.CareOrderItem;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 开诊细表(HisCareOrderItem)表数据库访问层
 * 
 * @author makejava
 * @since 2023-03-27 15:41:40
 */
@Repository
public interface HisCareOrderItemMapper extends BaseMapper<CareOrderItem> {

    List<CareOrderItem> selectListtype(String coId);
}
