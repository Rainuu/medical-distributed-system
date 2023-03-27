package com.aaa.check.service;


import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.CheckItemVo;
import com.aaa.core.vo.Result;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

/**
 * 开诊细表(HisCareOrderItem)表服务接口
 *
 * @author makejava
 * @since 2023-03-27 10:56:57
 */
public interface CareOrderItemService {


    Result<List<CareOrderItem>> selectAll(CheckItemVo checkItemVo);
}
