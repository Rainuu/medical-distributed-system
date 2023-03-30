package com.aaa.charge.service;


import com.aaa.charge.vo.PostObjVo;
import com.aaa.charge.vo.PostObjVo1;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 病例表(HisCareHistory)表服务接口
 *
 * @author makejava
 * @since 2023-03-27 09:37:34
 */

public interface HisCareChargeltemService {


    Result<List<CareOrderItem>> updateBystatus(PostObjVo postObjVo);
}
