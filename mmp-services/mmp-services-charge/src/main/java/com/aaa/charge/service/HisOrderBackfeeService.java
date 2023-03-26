package com.aaa.charge.service;


import com.aaa.charge.vo.OrderBackfeeVo;
import com.aaa.core.entity.DictData;
import com.aaa.core.entity.OrderBackfee;
import com.aaa.core.entity.OrderBackfeeItem;
import com.aaa.core.entity.OrderCharge;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 退费主表(HisOrderBackfee)表服务接口
 *
 * @author makejava
 * @since 2023-03-25 11:09:21
 */
public interface HisOrderBackfeeService {
    //分页模糊
    Result<IPage<OrderBackfee>> findAll(Integer curr, Integer size, OrderBackfeeVo backfeeVo);
    //查询所有数据根据id返回一条数据
    Result<List<OrderBackfeeItem>> selectAll(String backId);
    //字典所有属性
    Result<List<DictData>> selectdictionary();
}
