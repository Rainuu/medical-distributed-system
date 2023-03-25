package com.aaa.charge.dao;

import com.aaa.core.entity.OrderCharge;
import com.aaa.core.entity.OrderChargeItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 收费表(HisOrderCharge)表数据库访问层
 * 
 * @author makejava
 * @since 2023-03-24 09:56:27
 */

public interface HisOrderChargeMapper extends BaseMapper<OrderCharge> {


    boolean updByStatusType(String orderId);
}