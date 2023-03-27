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
      wrapper1.eq("ch_id",chId);
      CareOrder careOrder = hisCareOrderMapper.selectOne(wrapper1);

      //根据co_id获取CareOrderItem表的数据
      String coId = careOrder.getCoId();
      QueryWrapper<CareOrderItem> wrapper2 = new QueryWrapper<>();
      wrapper2.eq("co_id",coId);
      //eq对比状态是0通过
      wrapper2.eq("status",0);
      List<CareOrderItem> careOrderItem = hisCareOrderItemMapper.selectList(wrapper2);
      if (careOrderItem.size()!=0) {
         //计算订单金额
         Double sum = careOrderItem.stream()
                 .map(item -> item.getNum()
                         .multiply(item.getAmount())
                         .doubleValue()).reduce((a, b) -> a + b).get();


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
         map.put("careOrderItem", careOrderItem);
         map.put("sum", sum);
         return new Result<>(200, "成功", map);
      }
      return new Result<>(500,"失败");
   }
}