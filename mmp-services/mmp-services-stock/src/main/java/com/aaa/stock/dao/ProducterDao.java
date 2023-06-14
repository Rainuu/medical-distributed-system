package com.aaa.stock.dao;

import com.aaa.core.entity.Producer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@Mapper
public interface ProducterDao extends BaseMapper<Producer> {

    // 新增————@Param("producter") 定义别名 默认param1
    int updById(@Param("producer") Producer producer);
}
