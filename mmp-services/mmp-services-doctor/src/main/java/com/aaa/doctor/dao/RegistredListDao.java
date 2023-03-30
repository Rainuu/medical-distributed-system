package com.aaa.doctor.dao;

import com.aaa.core.entity.Registration;
import com.aaa.core.entity.Scheduling;
import com.aaa.core.vo.Result;
import com.aaa.doctor.vo.SchedulingVoo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

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



    /**
     * 查询医生排班
     */

}
