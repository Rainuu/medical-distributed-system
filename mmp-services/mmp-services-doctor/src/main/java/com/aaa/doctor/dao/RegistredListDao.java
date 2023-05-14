package com.aaa.doctor.dao;

import com.aaa.core.entity.Registration;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/27 11:37
 */
public interface RegistredListDao extends BaseMapper<Registration> {


    Integer handleSuccess(String registrationId);

    Integer handleReturn(String registrationId);

    Integer handleFalse(String registrationId);

    int updStatus(@Param("registrationId") String registrationId, @Param("status") String status);

    /**
     * 查询医生排班
     */

}
