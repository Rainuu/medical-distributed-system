package com.aaa.charge.service;


import com.aaa.core.vo.Result;

import java.util.Map;

/**
 * 病例表(HisCareHistory)表服务接口
 *
 * @author makejava
 * @since 2023-03-27 09:37:34
 */
public interface HisCareHistoryService {

    //处方收费 根据regid 查询三张表数据
    Result<Map<String, Object>> findcareHistory(String regId);

}
