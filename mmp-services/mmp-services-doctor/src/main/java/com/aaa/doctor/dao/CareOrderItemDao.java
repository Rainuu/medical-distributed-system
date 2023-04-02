package com.aaa.doctor.dao;

import com.aaa.core.entity.CareOrder;
import com.aaa.core.entity.CareOrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/26 19:50
 */
public interface CareOrderItemDao extends BaseMapper<CareOrderItem> {
    List<CareOrderItem> getOrderItem(@Param("patientId") String patientId,@Param("chId") String chId);
}
