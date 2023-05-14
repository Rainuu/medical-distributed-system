package com.aaa.doctor.dao;

import com.aaa.core.entity.CareHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/25 10:21
 */
public interface CareHistoryDao extends BaseMapper<CareHistory> {

    List<CareHistory> selectAll(String patientId);
}
