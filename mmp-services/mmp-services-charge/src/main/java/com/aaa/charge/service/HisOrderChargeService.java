package com.aaa.charge.service;


import com.aaa.charge.vo.OrderChargeVo;
import com.aaa.core.entity.DictData;
import com.aaa.core.entity.OrderCharge;
import com.aaa.core.entity.OrderChargeItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收费表(HisOrderCharge)表服务接口
 *
 * @author makejava
 * @since 2023-03-24 09:56:35
 */

public interface HisOrderChargeService {
    ////分页模糊
    Result<IPage<OrderCharge>> findAll(Integer curr, Integer size, OrderChargeVo chargeVo);
    //查询所有数据根据id返回一条数据
    Result<List<OrderChargeItem>> finddetail(String orderId);
    //字典所有属性
    Result<List<DictData>> CharFeign();
    //现金支付
    Result updByStatusType(String orderId);
}