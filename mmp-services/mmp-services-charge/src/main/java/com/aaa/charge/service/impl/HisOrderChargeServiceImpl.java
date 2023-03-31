package com.aaa.charge.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aaa.charge.dao.*;
import com.aaa.charge.fegin.OrderCharFeign;
import com.aaa.charge.service.HisOrderChargeService;
import com.aaa.charge.util.MyAlipayUtil;
import com.aaa.charge.vo.OrderChargeVo;
import com.aaa.core.entity.*;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收费表(HisOrderCharge)表服务实现类
 *
 * @author makejava
 * @since 2023-03-24 09:56:37
 */
@Service
public class HisOrderChargeServiceImpl implements HisOrderChargeService {
   @Autowired
   private HisOrderChargeMapper hisOrderChargeMapper;
   @Autowired
   private HisOrderChargeItemMapper hisOrderChargeItemMapper;
   @Autowired
   private OrderCharFeign orderCharFeign;
   @Autowired
   private HisCareHistoryMapper hisCareHistoryMapper;
   @Autowired
   private HisCareOrderMapper hisCareOrderMapper;
   @Autowired
   private HisCareOrderItemMapper hisCareOrderItemMapper;
   @Override
   //分页模糊查询所有数据
   public Result<IPage<OrderCharge>> findAll(Integer curr, Integer size, OrderChargeVo chargeVo) {
      IPage<OrderCharge> page = new Page(curr,size);
      QueryWrapper<OrderCharge> wrapper = new QueryWrapper<>();
      if (StringUtils.hasText(chargeVo.getPatientName())){
         wrapper.like("patient_name",chargeVo.getPatientName());
      }
      if (StringUtils.hasText(chargeVo.getRegId())){
         wrapper.like("reg_id",chargeVo.getRegId());
      }
      IPage<OrderCharge> orderChargeIPage = hisOrderChargeMapper.selectPage(page, wrapper);
      return new Result<>(200,"查询成功",orderChargeIPage);
   }

   @Override
   //分页模糊查询所有数据
   public Result<List<OrderChargeItem>> finddetail(String orderId) {
      List<OrderChargeItem> orderChargeItems = hisOrderChargeItemMapper.selectListAll(orderId);
      return new Result<>(200,"查询成功",orderChargeItems);
   }

   @Override
   //调用系统模块完成字典查询
   public Result<List<DictData>> CharFeign() {
      Result<List<DictData>> all = new Result<>(200,null,orderCharFeign.getAll());
      return all;
   }

   @Override
   public Result updByStatusType(String orderId) {
      boolean upd = hisOrderChargeMapper.updByStatusType(orderId);
      return new Result(200,"修改成功",upd);
   }

   @Override
   public Result<Map<String, Object>> selectupdById(String regId) {
      QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
      wrapper.eq("reg_id",regId);
      CareHistory careHistory=hisCareHistoryMapper.selectOne(wrapper);
      if (careHistory==null){
         return new Result<Map<String,Object>>(500,"单号错误",null);
      }
      //获取ch_id关联his_care_order表，获取co_id
      String chId  = careHistory.getChId();
      QueryWrapper<CareOrder> wrapper1 = new QueryWrapper<>();
      wrapper1.eq("ch_id",chId);
      List<CareOrder> careOrders  = hisCareOrderMapper.selectList(wrapper1);
      List<Map<String,Object>> list = new ArrayList<>();
      if (careOrders.size()>0){
         for (CareOrder careOrder:careOrders){
            //BeanUtil.beanToMap把对象转换为Map类型
            Map<String,Object>map1= BeanUtil.beanToMap(careOrder);
            QueryWrapper<CareOrderItem> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("status",1);
            wrapper2.eq("co_id",careOrder.getCoId());
            List<CareOrderItem> careOrderItem = hisCareOrderItemMapper.selectList(wrapper2);
            //根据order表关联order_item表
            //计算出订单总金额
            if (careOrderItem.size()!=0){

               map1.put("careOrderItem", careOrderItem);

               list.add(map1);
            }

         }
      }
      //判断是否未空isEmpty()
      if (list.isEmpty()) {
         return new Result<>(402, "请输入未支付的订单", null);
      }else {
         //把数据放入map返回
         HashMap<String, Object> map = new HashMap<>();
         map.put("careHistory", careHistory);
         map.put("careOrders", list);
         return new Result<>(200, "成功", map);
      }
   }


}