package com.aaa.charge.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aaa.charge.dao.*;
import com.aaa.charge.fegin.OrderCharFeign;
import com.aaa.charge.service.HisOrderChargeService;
import com.aaa.charge.util.HttpClient;
import com.aaa.charge.util.MyAlipayUtil;
import com.aaa.charge.vo.OrderChargeVo;
import com.aaa.charge.vo.PostObjVo;
import com.aaa.charge.vo.PostObjVo1;
import com.aaa.core.entity.*;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

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
   @Value("${weixin.appid}")
   private String appId;
   @Value("${weixin.mch_id}")
   private String mchId;
   @Value("${weixin.api_key}")
   private String apiKey;
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
            wrapper2.eq("item_type",0);
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

   @Override
   @Transactional
   public Result<List<CareOrderItem>> ZFB(PostObjVo postObjVo) {
      //生成orderId
      String orderId= "ODC";
      Random random = new Random();
      for (int i = 0; i < 10; i++) {
         orderId += String.valueOf(random.nextInt(10));
      }
      //创建HttpClient对象 作用远程调用
      HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
      //支持https协议
      client.setHttps(true);
      //设置请求的参数--格式为xml
      Map<String, String> params = new HashMap<>();//请求参数
      params.put("appid", appId);//公众号ID
      params.put("mch_id", mchId);//商品号id
      params.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
      params.put("body", postObjVo.getOrderChargeDto().getPatientName());//标题
      params.put("out_trade_no", orderId);//订单号
      //支付金额0.01 未来换成真是的金额
      params.put("total_fee", new BigDecimal(0.01).multiply(new BigDecimal(100)).longValue() + "");
      params.put("spbill_create_ip", "127.0.0.1");//未来写成项目部署的ip
      params.put("notify_url", "http:localhost:8090/charge/api/hisOrderCharge");
      params.put("trade_type", "NATIVE");//支付方式使用PC
      //设置请求参数
      try {
         client.setXmlParam(WXPayUtil.generateSignedXml(params, apiKey));
         //发送请求
         client.post();
         //获取请求的相应结果
         String content = client.getContent();
         Map<String, String> map = WXPayUtil.xmlToMap(content);;
         if (map.get("result_code").equals("SUCCESS")){
            Map<String,Object> result=new HashMap<>();
            result.put("codeUrl",map.get("code_url"));
            result.put("price",postObjVo.getOrderChargeDto().getOrderAmount());
            result.put("orderId",orderId);
            //创建订单
            //获取挂号单id
            String regId = postObjVo.getOrderChargeDto().getRegId();
            //获取患者姓名
            String patientName = postObjVo.getOrderChargeDto().getPatientName();
            //获取总金额
            BigDecimal orderAmount = postObjVo.getOrderChargeDto().getOrderAmount();
            //创建时间
            LocalDateTime createTime = LocalDateTime.now();
            hisOrderChargeMapper.insertAllWX(orderId,regId,patientName,orderAmount,createTime);
            //获取itemId修改状态
            List<PostObjVo1> orderChargeItemDtoList = postObjVo.getOrderChargeItemDtoList();
            for (int i=0;i<orderChargeItemDtoList.size();i++){
               hisCareOrderItemMapper.updBystatus(orderChargeItemDtoList.get(i).getItemId());
            }
            for (int i=0;i<orderChargeItemDtoList.size();i++) {
               OrderChargeItem orderChargeItem = new OrderChargeItem();
               orderChargeItem.setOrderId(orderId);
               orderChargeItem.setItemId(orderChargeItemDtoList.get(i).getItemId());
               orderChargeItem.setItemType(orderChargeItemDtoList.get(i).getItemType());
               orderChargeItem.setItemName(orderChargeItemDtoList.get(i).getItemName());
               orderChargeItem.setItemNum(orderChargeItemDtoList.get(i).getItemNum());
               orderChargeItem.setItemPrice(orderChargeItemDtoList.get(i).getItemPrice());
               orderChargeItem.setItemAmount(orderChargeItemDtoList.get(i).getItemAmount());
               orderChargeItem.setCoId(orderChargeItemDtoList.get(i).getCoId());
               orderChargeItem.setStatus("0");
               hisOrderChargeItemMapper.insert(orderChargeItem);
            }
            return new Result(200,"生成二维码成功",result);
         }

      } catch (Exception e) {
         e.printStackTrace();
   }
      return new Result<>(200,"成功");
   }


   @Override
   @Transactional
   public Result<List<OrderCharge>> updByStatus(String orderId) {
      Boolean flag=false;
      try {
         //根据订单状态查询微信的支付的状态
         HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
         Map<String, String> params = new HashMap<>();
         params.put("appid", appId);
         params.put("mch_id", mchId);
         params.put("out_trade_no", orderId);
         params.put("nonce_str", WXPayUtil.generateNonceStr());
         client.setHttps(true);
         client.setXmlParam(WXPayUtil.generateSignedXml(params,apiKey));
         client.post();
         String content = client.getContent();
         System.out.println(content);
         Map<String, String> map = WXPayUtil.xmlToMap(content);
         if (map.get("trade_state").equals("SUCCESS")){
            flag=true;
            //1修改订单状态
            hisOrderChargeMapper.updBystatus(orderId);
            QueryWrapper<OrderChargeItem> wrapper = new QueryWrapper<>();
            wrapper.eq("order_id",orderId);
            List<OrderChargeItem> orderChargeItems = hisOrderChargeItemMapper.selectList(wrapper);
            for (int i =0;orderChargeItems.size()>i;i++){
               hisOrderChargeItemMapper.updateBystatus(orderId);
            }
            return  new Result(2000,"支付成功",flag);
         }
      }catch (Exception e){

      }
      return new Result<>(200,"支付成功",null);
   }

   //处方发药
   @Override
   public Result updByDispense(String[] itemId) {
      for (int i=0;itemId.length>i;i++){
         hisOrderChargeItemMapper.updByDispense(itemId[i]);
      }
      for (int i=0;itemId.length>i;i++){
         hisCareOrderItemMapper.updstatus(itemId[i]);
      }
      //调用fen接口 传入名字和数量

      return new Result<>(200,"发药成功");
   }




   //统计接口
   @Override
   public List<OrderCharge> listAll(String[] create_time) {
      QueryWrapper<OrderCharge> wrapper = new QueryWrapper<>();
      wrapper.between("create_time",create_time[0],create_time[1]);
      return hisOrderChargeMapper.selectList(wrapper);
   }
   //统计接口
   @Override
   public List<OrderChargeItem> listAllItem(String[] create_time) {
      QueryWrapper<OrderChargeItem> wrapper = new QueryWrapper<>();
      wrapper.between("create_time",create_time[0],create_time[1]);
      return hisOrderChargeItemMapper.selectList(wrapper);
   }



}