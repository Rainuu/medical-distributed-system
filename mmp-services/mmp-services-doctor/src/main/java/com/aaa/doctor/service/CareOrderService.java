package com.aaa.doctor.service;

import com.aaa.core.entity.CareOrder;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/26 19:46
 */
public interface CareOrderService {
    List<CareOrder> getCareOrder(String chId);


}
