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
    Result<IPage<OrderCharge>> findAll(Integer curr, Integer size, OrderChargeVo chargeVo);

    Result<List<OrderChargeItem>> finddetail(String orderId);

    Result<List<DictData>> CharFeign();

    Result updByStatusType(String orderId);
}
