package com.aaa.stock.dao;

import com.aaa.core.entity.Producer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/24 9:40
 */
@Mapper
public interface ProducterDao extends BaseMapper<Producer> {

    //插入数据
    // @Param("producter") 定义别名
    int updById(@Param("producer") Producer producer);
}
