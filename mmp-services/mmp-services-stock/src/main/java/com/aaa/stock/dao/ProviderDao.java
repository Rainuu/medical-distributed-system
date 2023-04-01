package com.aaa.stock.dao;

import com.aaa.core.entity.Provider;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/26 15:40
 */
@Mapper
public interface ProviderDao extends BaseMapper<Provider> {

    //插入数据
    int updById(@Param("provider") Provider provider);  //@Param("provider")定义别名 默认param1

}
