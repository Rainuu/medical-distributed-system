package com.aaa.doctor.service;

import com.aaa.core.entity.Registration;
import com.aaa.core.vo.Result;
import com.aaa.doctor.vo.RegisteredListVo;
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
}
