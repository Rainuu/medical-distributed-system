package com.aaa.charge.dao;

import com.aaa.core.entity.OrderCharge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 收费表(HisOrderCharge)表数据库访问层
 * 
 * @author makejava
 * @since 2023-03-24 09:56:27
 */

public interface HisOrderChargeMapper extends BaseMapper<OrderCharge> {

    //现金支付
    boolean updByStatusType(String orderId);

    void insertAll(String orderId,String regId, String patientName, BigDecimal orderAmount, LocalDateTime createTime,LocalDateTime payTime);

    void insertAllWX(String orderId, String regId, String patientName, BigDecimal orderAmount, LocalDateTime createTime,LocalDateTime payTime);

    void updBystatus(String orderId);
}