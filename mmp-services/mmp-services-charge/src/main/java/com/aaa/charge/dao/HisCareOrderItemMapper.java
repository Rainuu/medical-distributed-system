package com.aaa.charge.dao;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.entity.OrderCharge;
import com.aaa.core.entity.OrderChargeItem;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 开诊细表(HisCareOrderItem)表数据库访问层
 * 
 * @author makejava
 * @since 2023-03-27 15:41:40
 */
@Repository
public interface HisCareOrderItemMapper extends BaseMapper<CareOrderItem> {


    void updateBystatus(String itemId);


    void updBystatus(String itemId);

    List<Map<String,Object>> getData(@Param("itemName") String itemName, @Param("dateRange1") String dateRange1, @Param("dateRange2")String dateRange2);


    void updstatus(String itemId);



    void updateitemId(QueryWrapper<CareOrderItem> wrapper1);
}
