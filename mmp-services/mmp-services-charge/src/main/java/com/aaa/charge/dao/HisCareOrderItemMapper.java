package com.aaa.charge.dao;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.entity.OrderCharge;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

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


    void updstatus(String itemId);
}
