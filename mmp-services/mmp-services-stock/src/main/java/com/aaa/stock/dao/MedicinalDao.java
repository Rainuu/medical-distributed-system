package com.aaa.stock.dao;

import com.aaa.core.entity.Medicines;
import com.aaa.stock.vo.MedicinalVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/27 14:29
 */
public interface MedicinalDao extends BaseMapper<Medicines> {

    // 查询和模糊查询

    IPage<Medicines> getMedById(MedicinalVo medicinalVo, Page page);

}
