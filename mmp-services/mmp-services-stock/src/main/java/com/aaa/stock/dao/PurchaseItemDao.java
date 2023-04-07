package com.aaa.stock.dao;

import com.aaa.core.entity.PurchaseItem;
import com.aaa.stock.vo.PurchaseItemVo;
import com.aaa.stock.vo.editPurchaseVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/4/3 7:54
 */
@Mapper
public interface PurchaseItemDao extends BaseMapper<PurchaseItem> {

    List<PurchaseItem> getPurchaseItemById(String purchaseId);

    // 用Map<String,object>做返回值容器也可接多表参数，但前端的字段和数据库字段一致，没有经过Vo加工
    List<editPurchaseVo> getAllById(String purchaseId);

    List<PurchaseItem> getByPurchaseId(String purchaseId);

    void delByPurchaseId(@Param("purchaseId") String purchaseId);
}
