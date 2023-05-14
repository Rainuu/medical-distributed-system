package com.aaa.charge.service;


import com.aaa.charge.vo.OrderBackfeeVo;
import com.aaa.core.entity.DictData;
import com.aaa.core.entity.OrderBackfee;
import com.aaa.core.entity.OrderBackfeeItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * 退费主表(HisOrderBackfee)表服务接口
 *
 * @author makejava
 * @since 2023-03-25 11:09:21
 */
public interface HisOrderBackfeeService {
    //退费列表查询,分页模糊
    Result<IPage<OrderBackfee>> findAll(Integer curr, Integer size, OrderBackfeeVo backfeeVo);

    //退费列表,查看详情,获取所有数据,根据id返回一条数据
    Result<List<OrderBackfeeItem>> selectAll(String backId);

    //字典所有属性
    Result<List<DictData>> selectdictionary();

    //处方退费,查询所有状态为1已支付的数据
    Result<Map<String, Object>> selectByregId(String regId);

    //统计接口
    List<OrderBackfee> listAll(String[] create_time);

    //统计接口
    List<OrderBackfeeItem> listAllitem(String[] create_time);
}
