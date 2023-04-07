package com.aaa.statistics.service.Impl;

import com.aaa.core.entity.OrderBackfee;
import com.aaa.core.entity.OrderCharge;
import com.aaa.core.vo.Result;
import com.aaa.statistics.RevenueVo;
import com.aaa.statistics.feign.RevenueFeign;
import com.aaa.statistics.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RevenueServiceImpl implements RevenueService {
@Autowired
private RevenueFeign revenueFeign;
    @Override
    public Result queryInfo(String dateRange1,String dateRange2) {
        LocalDate dateTime= LocalDate.now();
        String[] createTime=new String[]{dateRange1,dateRange2};
        if (dateRange1==null&&dateRange2==null){
            createTime= new String[]{String.valueOf(dateTime), String.valueOf(dateTime.plusDays(1))};

        }
        RevenueVo revenueVo = new RevenueVo();
        //声明总收入
        BigDecimal toll=new BigDecimal(0);
        List<OrderCharge> list = revenueFeign.InComelist(createTime);
        for (OrderCharge item:list) {
            if(item.getOrderStatus().equals("1")){
                toll=toll.add(item.getOrderAmount());
            }
        };
        revenueVo.setToll(toll);
        //声明总现金收入
        BigDecimal cashIncome=new BigDecimal(0);
        for (OrderCharge item:list) {
            if(item.getOrderStatus().equals("1")&&item.getPayType().equals("0")){
                cashIncome=cashIncome.add(item.getOrderAmount());
            }
        };
        revenueVo.setCashIncome(cashIncome);
        //声明支付宝收入
        BigDecimal alipayIncome=new BigDecimal(0);
        for (OrderCharge item:list) {
            if(item.getOrderStatus().equals("1")&&item.getPayType().equals("1")){
                alipayIncome=alipayIncome.add(item.getOrderAmount());
            }
        };
        revenueVo.setAlipayIncome(alipayIncome);
        //获取退费列表
        List<OrderBackfee> orderBackfees = revenueFeign.BackFeelist(createTime);
        //声明总退费
        BigDecimal refund=new BigDecimal(0);
        for (OrderBackfee item: orderBackfees) {
            if (item.getBackStatus().equals("1")){
                refund=refund.add(new BigDecimal(item.getBackAmount()));
            }
        }
        revenueVo.setRefund(refund);
        //声明现金退费
        BigDecimal cashRefund=new BigDecimal(0);
        for (OrderBackfee item: orderBackfees) {
            if (item.getBackStatus().equals("1")&&item.getBackType().equals("0")){
                cashRefund=cashRefund.add(new BigDecimal(item.getBackAmount()));
            }
        }
        revenueVo.setCashRefund(cashRefund);
        //声明支付宝退费
        BigDecimal alipayRefund=new BigDecimal(0);
        for (OrderBackfee item: orderBackfees) {
            if (item.getBackStatus().equals("1")&&item.getBackType().equals("1")){
                alipayRefund=alipayRefund.add(new BigDecimal(item.getBackAmount()));
            }
        }
        revenueVo.setAlipayRefund(alipayRefund);
        revenueVo.setTotalRevenue(revenueVo.getToll().subtract(revenueVo.getRefund()));
        return new Result(200,null,revenueVo);
    }
}
