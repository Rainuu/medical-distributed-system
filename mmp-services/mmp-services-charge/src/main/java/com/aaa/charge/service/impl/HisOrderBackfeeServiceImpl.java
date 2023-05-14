package com.aaa.charge.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aaa.charge.dao.*;
import com.aaa.charge.fegin.OrderCharFeign;
import com.aaa.charge.service.HisOrderBackfeeService;
import com.aaa.charge.vo.OrderBackfeeVo;
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
 * 退费主表(HisOrderBackfee)表服务实现类
 *
 * @author makejava
 * @since 2023-03-25 11:09:21
 */
@Service
public class HisOrderBackfeeServiceImpl implements HisOrderBackfeeService {
   @Autowired
   private HisOrderBackfeeMapper hisOrderBackfeeDao;
   @Autowired
   private HisOrderBackfeeItemMapper hisOrderBackfeeItemMapper;
   @Autowired
   private OrderCharFeign orderCharFeign;
   @Autowired
   private HisCareHistoryMapper hisCareHistoryMapper;
   @Autowired
   private HisCareOrderMapper hisCareOrderMapper;
   @Autowired
   private HisCareOrderItemMapper hisCareOrderItemMapper;


   @Override
   //退费列表查询,分页获取所有数据库数据
   public Result<IPage<OrderBackfee>> findAll(Integer curr, Integer size, OrderBackfeeVo backfeeVo) {
      IPage<OrderBackfee> page = new Page<>(curr, size);
      QueryWrapper<OrderBackfee> wrapper = new QueryWrapper<>();
      if (StringUtils.hasText(backfeeVo.getPatientName())) {
         wrapper.like("patient_name", backfeeVo.getPatientName());
      }
      if (StringUtils.hasText(backfeeVo.getRegId())) {
         wrapper.like("reg_id", backfeeVo.getRegId());
      }
      IPage<OrderBackfee> page1 = hisOrderBackfeeDao.selectPage(page, wrapper);
      return new Result<>(200, "查询成功", page1);
   }

   @Override
   //退费列表,查看详情,获取所有数据,根据id返回一条数据
   public Result<List<OrderBackfeeItem>> selectAll(String backId) {
      List<OrderBackfeeItem> select = hisOrderBackfeeDao.selectAll(backId);
      return new Result<>(200, "查询成功", select);
   }

   @Override
   //调用系统模块完成字典查询
   public Result<List<DictData>> selectdictionary() {
      Result<List<DictData>> findall = new Result<>(200, "字典查询成功", orderCharFeign.getAll());
      return findall;
   }

   //处方退费,查询所有状态为1已支付的数据
   @Override
   public Result<Map<String, Object>> selectByregId(String regId) {
      QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
      wrapper.eq("reg_id", regId);
      CareHistory careHistory = hisCareHistoryMapper.selectOne(wrapper);
      if (careHistory == null) {
         return new Result<Map<String, Object>>(500, "单号错误", null);
      }
      //获取ch_id关联his_care_order表，获取co_id
      String chId = careHistory.getChId();
      QueryWrapper<CareOrder> wrapper1 = new QueryWrapper<>();
      wrapper1.eq("ch_id", chId);
      List<CareOrder> careOrders = hisCareOrderMapper.selectList(wrapper1);
      List<Map<String, Object>> list = new ArrayList<>();
      if (careOrders.size() > 0) {
         for (CareOrder careOrder : careOrders) {
            //BeanUtil.beanToMap把对象转换为Map类型
            Map<String, Object> map1 = BeanUtil.beanToMap(careOrder);
            QueryWrapper<CareOrderItem> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("status", 1);
            wrapper2.eq("co_id", careOrder.getCoId());
            List<CareOrderItem> careOrderItem = hisCareOrderItemMapper.selectList(wrapper2);
            //根据order表关联order_item表
            //计算出订单总金额
            if (careOrderItem.size() != 0) {
               map1.put("careOrderItem", careOrderItem);
               list.add(map1);
            }
         }
      }
      //判断是否未空isEmpty()
      if (list.isEmpty()) {
         return new Result<>(402, "请输入未支付的订单", null);
      } else {
         //把数据放入map返回
         HashMap<String, Object> map = new HashMap<>();
         map.put("careHistory", careHistory);
         map.put("careOrders", list);
         return new Result<>(200, "成功", map);
      }
   }


   //统计数据  退费接口
   @Override
   public List<OrderBackfee> listAll(String[] create_time) {
      QueryWrapper<OrderBackfee> wrapper = new QueryWrapper<>();
      wrapper.between("create_time", create_time[0], create_time[1]);
      return hisOrderBackfeeDao.selectList(wrapper);
   }

   //统计数据  退费接口
   @Override
   public List<OrderBackfeeItem> listAllitem(String[] create_time) {
      QueryWrapper<OrderBackfeeItem> wrapper = new QueryWrapper<>();
      wrapper.between("create_time", create_time[0], create_time[1]);
      return hisOrderBackfeeItemMapper.selectList(wrapper);
   }


}
