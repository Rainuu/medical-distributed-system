package com.aaa.stock.dao;

import com.aaa.core.entity.Provider;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@Mapper
public interface ProviderDao extends BaseMapper<Provider> {

    //插入数据
    int updById(@Param("provider") Provider provider);  //@Param("provider")定义别名 默认param1

}
