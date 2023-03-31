package com.aaa.charge.service;


import com.aaa.charge.vo.OrderBackfeeVo;
import com.aaa.charge.vo.PostObjVo;
import com.aaa.charge.vo.PostObjVoV;
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
public interface HisOrderBackfeeItemService {

    Result<List<OrderBackfeeItem>> updateBystatus(PostObjVoV postObjVov);
}
