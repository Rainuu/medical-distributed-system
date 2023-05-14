package com.aaa.doctor.dao;

import com.aaa.core.entity.CareOrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/25 18:09
 */
public interface CareItemDao extends BaseMapper<CareOrderItem> {
    List<CareOrderItem> getOrderItem(String patientId, String chId);

//    List<CareOrderItem>findOrderItem(String coId);
}
