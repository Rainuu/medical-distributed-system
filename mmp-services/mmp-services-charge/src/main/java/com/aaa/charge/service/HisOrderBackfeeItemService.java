package com.aaa.charge.service;


import com.aaa.charge.vo.PostObjVoV;
import com.aaa.core.entity.OrderBackfeeItem;
import com.aaa.core.vo.Result;

import java.util.List;

/**
 * 退费主表(HisOrderBackfee)表服务接口
 */
public interface HisOrderBackfeeItemService {

    //处方退费：现金退费按钮
    Result<List<OrderBackfeeItem>> updateBystatus(PostObjVoV postObjVov);

    //处方退费：微信退费按钮
    Result<List<OrderBackfeeItem>> updBystatus(PostObjVoV postObjVoV);
}
