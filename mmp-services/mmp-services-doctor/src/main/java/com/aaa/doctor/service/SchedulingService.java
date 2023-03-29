package com.aaa.doctor.service;

import com.aaa.core.entity.User;
import com.aaa.core.vo.Result;
import com.aaa.doctor.vo.SchedulingVo;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/29 9:54
 */
public interface SchedulingService {

    Result queryScheduling(SchedulingVo schedulingVo);

    List<User> queryUserName();

    // 我的排班
    Result MyqueryScheduling(SchedulingVo schedulingVo);
}
