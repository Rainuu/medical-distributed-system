package com.aaa.charge.service;


import com.aaa.charge.vo.OrderChargeVo;
import com.aaa.charge.vo.PostObjVo;
import com.aaa.core.entity.*;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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


    Result<Map<String, Object>> selectupdById(String regId);

    Result<List<CareOrderItem>> ZFB(PostObjVo postObjVo);

   List<OrderCharge> listAll(String[] create_time);

    Result<List<OrderCharge>> updByStatus(String orderId);

    Result updByDispense(String[] itemId);

    List<OrderChargeItem> listAllItem(String[] create_time);

    Result<List<CareOrder>> updateBystatus(String orderId);

    Result<List<OrderCharge>> updBystatus1(String orderId);
}
