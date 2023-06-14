package com.aaa.stock.dao;

import com.aaa.core.entity.Medicines;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
public interface MedicinalDao extends BaseMapper<Medicines> {

    // 查询和模糊查询
    // IPage<Medicines> getMedById(MedicinalVo medicinalVo, Page page);

    // 根据id修改
    int updById(@Param("medicines") Medicines medicines);
}
