package com.aaa.charge.service.impl;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import com.aaa.charge.dao.HisCareOrderItemMapper;
import com.aaa.charge.dao.HisOrderChargeItemMapper;
import com.aaa.charge.dao.HisOrderChargeMapper;
import com.aaa.charge.service.HisCareChargeltemService;
import com.aaa.charge.vo.PostObjVo;
import com.aaa.charge.vo.PostObjVo1;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.entity.OrderCharge;
import com.aaa.core.entity.OrderChargeItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 病例表(HisCareHistory)表服务接口
 *
 * @author makejava
 * @since 2023-03-27 09:37:34
 */
@Service
public class HisCareChargeltemImpl implements HisCareChargeltemService {

    @Autowired
    private HisCareOrderItemMapper hisCareOrderItemMapper;
    @Autowired
    private HisOrderChargeMapper hisOrderChargeMapper;
    @Autowired
    private HisOrderChargeItemMapper hisOrderChargeItemMapper;

    @Override
    @Transactional//开启事务
    public Result<List<CareOrderItem>> updateBystatus(PostObjVo postObjVo) {
        //获取挂号单id
        String regId = postObjVo.getOrderChargeDto().getRegId();
        //获取患者姓名
        String patientName = postObjVo.getOrderChargeDto().getPatientName();
        //获取总金额
        BigDecimal orderAmount = postObjVo.getOrderChargeDto().getOrderAmount();
        //支付时间
        LocalDateTime payTime = LocalDateTime.now();
        //创建时间
        LocalDateTime createTime = LocalDateTime.now();
        //生成orderId
        String orderId= "ODC";
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            orderId += String.valueOf(random.nextInt(10));
        }
        hisOrderChargeMapper.insertAll(orderId,regId,patientName,orderAmount,createTime,payTime);

        //获取itemId修改状态
        List<PostObjVo1> orderChargeItemDtoList = postObjVo.getOrderChargeItemDtoList();
        for (int i=0;i<orderChargeItemDtoList.size();i++){
            hisCareOrderItemMapper.updateBystatus(orderChargeItemDtoList.get(i).getItemId());
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
            orderChargeItem.setStatus("1");
            hisOrderChargeItemMapper.insert(orderChargeItem);
        }

        return new Result<>(200,"支付成功");
    }


}
