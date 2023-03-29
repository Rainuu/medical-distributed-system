package com.aaa.doctor.dao;

import com.aaa.core.entity.CareOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/26 19:50
 */
public interface CareOrderDao extends BaseMapper<CareOrder> {
    List<CareOrder> selectList(String chId);
}
