package com.aaa.doctor.service;

import com.aaa.core.entity.Registration;

import java.util.List;
import java.util.Map;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/30 22:39
 */
public interface RegistrationService {


    /*
     * 查询新开就诊中的就诊列表状态
     * */
    List<Registration> queryRegistrationStatus1(String status, String schedulingType);

    // 给挂号单添加挂单号
    Boolean insertRegistration(String userId, String regItemAmount, Integer regItemId, Map<String, Object> obj);

    // 根据部门号查询编号最大的编号
    Integer getRagNumber(Long deptId);


}


