package com.aaa.stock.dao;

import com.aaa.core.entity.PurchaseItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/4/3 7:54
 */
@Mapper
public interface PurchaseItemDao extends BaseMapper<PurchaseItem> {

    List<PurchaseItem> getPurchaseItemById(String purchaseId);

}
