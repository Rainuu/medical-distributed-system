package com.aaa.charge.service;


import com.aaa.charge.vo.OrderChargeVo;
import com.aaa.charge.vo.PostObjVo;
import com.aaa.core.entity.*;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * 收费表(HisOrderCharge)表服务接口
 *
 * @author makejava
 * @since 2023-03-24 09:56:35
 */

public interface HisOrderChargeService {
    //收费列表,分页获取所有数据库数据
    Result<IPage<OrderCharge>> findAll(Integer curr, Integer size, OrderChargeVo chargeVo);

    //收费列表,查看详情
    Result<List<OrderChargeItem>> finddetail(String orderId);

    //字典所有属性
    Result<List<DictData>> CharFeign();

    //收费列表,现金支付
    Result updByStatusType(String orderId);

    //处方发药查询所有数据
    Result<Map<String, Object>> selectupdById(String regId);

    //处方收费:微信支付
    Result<List<CareOrderItem>> ZFB(PostObjVo postObjVo);

    //处方收费,微信支付:微信支付,支付成功后修改状态
    Result<List<OrderCharge>> updByStatus(String orderId);

    //处方发药  发药按钮实现
    Result updByDispense(String[] itemId);

    //收费列表:微信支付
    Result<List<CareOrder>> updateBystatus(String orderId);

    //统计接口
    List<OrderChargeItem> listAllItem(String[] create_time);

    //统计接口
    List<OrderCharge> listAll(String[] create_time);


}
