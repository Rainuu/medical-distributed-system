package com.aaa.charge.service.impl;

import com.aaa.charge.dao.HisCareOrderItemMapper;
import com.aaa.charge.dao.HisCareOrderMapper;
import com.aaa.charge.service.HisCareHistoryService;
import com.aaa.charge.dao.HisCareHistoryMapper;


import com.aaa.core.entity.CareHistory;
import com.aaa.core.entity.CareOrder;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 病例表(HisCareHistory)表服务实现类
 *
 * @author makejava
 * @since 2023-03-27 09:37:34
 */
@Service
public class HisCareHistoryServiceImpl implements HisCareHistoryService {
   @Autowired
   private HisCareHistoryMapper hisCareHistoryMapper;
   @Autowired
   private HisCareOrderMapper hisCareOrderMapper;
   @Autowired
   private HisCareOrderItemMapper hisCareOrderItemMapper;
   @Override
   public Result<Map<String,Object>> findcareHistory(String regId) {
      List<List<CareOrderItem>> list = new ArrayList<>();
      List<CareOrderItem> careOrderItem = null;
      QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
      wrapper.eq("reg_id",regId);
      //new CareHistory实体获取一行数据
      CareHistory careHistory=hisCareHistoryMapper.selectOne(wrapper);
      if (careHistory==null){
         return new Result(500,"单号错误",careHistory);
      }
      //获取ch_id关联his_care_order表，获取co_id
      String chId  = careHistory.getChId();
      QueryWrapper<CareOrder> wrapper1 = new QueryWrapper<>();
      System.out.println("asfdkhasofasohknsd"+chId);
      wrapper1.eq("ch_id",chId);
      List<CareOrder> careOrders  = hisCareOrderMapper.selectList(wrapper1);
      System.out.println("askfgaiohioasbkbfaeibaefbohje"+careOrders);

      for (int i= 0;i<careOrders.size();i++) {
         String coId = careOrders.get(i).getCoId();
         List<CareOrderItem> careOrderItems = hisCareOrderItemMapper.selectListtype(coId);
         list.add(careOrderItems);

         System.out.println("asfoahfioahioahf"+list);
         System.out.println("asfoahfioahioahf"+careOrderItems);
      }


//      if (careOrderItem.size()!=0) {
//         //计算订单金额
//         Double sum = careOrderItem.stream()
//                 .map(item -> item.getNum()
//                         .multiply(item.getAmount())
//                         .doubleValue()).reduce((a, b) -> a + b).get();
         //计算金额
//      BigDecimal num=null;
//      for (CareOrderItem c:careOrderItem){
//         BigDecimal num1 = c.getNum();
//         Integer num2=Integer.parseInt(num1.toString());
//         BigDecimal price = c.getPrice();
//         Integer price1=Integer.parseInt(price.toString());
//         num= BigDecimal.valueOf(num2*price1);
//      }
         //把数据放入map返回
         HashMap<String, Object> map = new HashMap<>();
         map.put("careHistory", careHistory);
         map.put("careOrderItem", list);
         return new Result<>(200, "成功", map);
//      }

   }
}