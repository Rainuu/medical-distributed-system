package com.aaa.doctor.dao;

import com.aaa.core.entity.Registration;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/31 20:36
 */
public interface ApiRegistrationDao extends BaseMapper<Registration> {

    @Select("select user_id,sum(registration_amount) rea,count(patient_id)pid from his_registration GROUP BY user_id having userId=#{user_id}")
    List<Registration> selectSum(String userId);


}
