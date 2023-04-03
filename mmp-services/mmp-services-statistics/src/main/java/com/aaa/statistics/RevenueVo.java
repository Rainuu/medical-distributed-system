package com.aaa.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueVo {
    private BigDecimal totalRevenue;
    private BigDecimal toll;
    private BigDecimal refund;
    private BigDecimal cashIncome;
    private BigDecimal alipayIncome;
    private BigDecimal cashRefund;
    private BigDecimal alipayRefund;
}
