package com.aaa.charge.service;


import com.aaa.charge.vo.PostObjVo;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.Result;

import java.util.List;

/**
 * 病例表(HisCareHistory)表服务接口
 */

public interface HisCareChargeltemService {

    //处方收费 现金支付修改状态
    Result<List<CareOrderItem>> updateBystatus(PostObjVo postObjVo);
}
