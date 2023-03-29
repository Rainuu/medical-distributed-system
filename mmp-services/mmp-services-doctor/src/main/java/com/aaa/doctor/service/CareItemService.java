package com.aaa.doctor.service;

import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.Result;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/25 18:08
 */
public interface CareItemService {
    Result<List<CareOrderItem>> FindByItem(String patientId,String chId);

    List<CareOrderItem>getCareOrderItem(String coId);
}
