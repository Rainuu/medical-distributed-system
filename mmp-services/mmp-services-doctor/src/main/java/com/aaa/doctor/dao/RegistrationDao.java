package com.aaa.doctor.dao;

import com.aaa.core.entity.Registration;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/30 22:48
 */
public interface RegistrationDao extends BaseMapper<Registration> {

    Integer getRegNumber(Long deptId);

    List<Registration> selectSum(String doctorName, String dateRange1, String dateRange2);

}
