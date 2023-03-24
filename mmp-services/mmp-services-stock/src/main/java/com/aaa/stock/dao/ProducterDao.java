package com.aaa.stock.dao;

import com.aaa.core.entity.Producer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/24 9:40
 */
public interface ProducterDao extends BaseMapper<Producer> {

    // 根据ID查询厂家信息
    List<Producer> getAll();

    //

}
