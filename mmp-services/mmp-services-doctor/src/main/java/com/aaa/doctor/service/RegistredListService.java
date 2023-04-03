package com.aaa.doctor.service;

import com.aaa.core.entity.Registration;
import com.aaa.core.entity.Scheduling;
import com.aaa.core.vo.Result;
import com.aaa.doctor.vo.RegisteredListVo;
import com.aaa.doctor.vo.SchedulingVoo;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/27 11:32
 */
public interface RegistredListService {

    Result<IPage<Registration>> getAllList(Integer currentPage, Integer pageSize,RegisteredListVo registeredListVo);


    Result<Integer> handleSuccess(String registrationId);

    Result<Integer> handleReturn(String registrationId);

    Result<Integer> handleFalse(String registrationId);

    /**
     * 查询医生排班表
     * @param
     * @return
     */
    Result<IPage<Scheduling>> findDoctocList(Integer currentPage, Integer pageSize,SchedulingVoo schedulingVoo);



    Boolean updRegistrationId(String registrationId, String status);
}
