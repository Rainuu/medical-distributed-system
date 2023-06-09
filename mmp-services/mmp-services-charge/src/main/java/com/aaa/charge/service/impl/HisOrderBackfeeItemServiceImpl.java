package com.aaa.charge.service.impl;


import com.aaa.charge.dao.HisOrderBackfeeItemMapper;
import com.aaa.charge.dao.HisOrderBackfeeMapper;
import com.aaa.charge.dao.HisOrderChargeMapper;
import com.aaa.charge.service.HisOrderBackfeeItemService;
import com.aaa.charge.vo.PostObjVo1;
import com.aaa.charge.vo.PostObjVoV;
import com.aaa.core.entity.OrderBackfeeItem;
import com.aaa.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * 退费主表(HisOrderBackfee)表服务接口
 */
@Service
public class HisOrderBackfeeItemServiceImpl implements HisOrderBackfeeItemService {
    @Autowired
    private HisOrderBackfeeMapper hisOrderBackfeeMapper;
    @Autowired
    private HisOrderBackfeeItemMapper hisOrderBackfeeItemMapper;
    @Autowired
    private HisOrderChargeMapper hisOrderChargeMapper;

    @Override
    @Transactional//开启事务
    //处方退费：现金退费按钮
    public Result<List<OrderBackfeeItem>> updateBystatus(PostObjVoV postObjVov) {
        //获取挂号单id
        String regId = postObjVov.getOrderBackfeeDto().getRegId();
        //获取患者姓名
        String patientName = postObjVov.getOrderBackfeeDto().getPatientName();
        //获取总金额
        BigDecimal backAmount = postObjVov.getOrderBackfeeDto().getBackAmount();
        //创建时间
        LocalDateTime createTime = LocalDateTime.now();
        //支付时间
        LocalDateTime backTime = LocalDateTime.now();
        //生成odbId
        String backId = "ODB";
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            backId += String.valueOf(random.nextInt(10));
        }

        hisOrderBackfeeMapper.insertAll(backId, regId, patientName, backAmount, createTime, backTime);
        //获取item状态
        List<PostObjVo1> orderBackfeeItemDtoList = postObjVov.getOrderBackfeeItemDtoList();
        for (int i = 0; i < orderBackfeeItemDtoList.size(); i++) {
            hisOrderBackfeeItemMapper.updateBystatus(orderBackfeeItemDtoList.get(i).getItemId());
        }
        for (int i = 0; i < orderBackfeeItemDtoList.size(); i++) {
            OrderBackfeeItem orderBackfeeItem = new OrderBackfeeItem();
            orderBackfeeItem.setBackId(backId);
            orderBackfeeItem.setItemId(orderBackfeeItemDtoList.get(i).getItemId());
            orderBackfeeItem.setItemType(orderBackfeeItemDtoList.get(i).getItemType());
            orderBackfeeItem.setItemName(orderBackfeeItemDtoList.get(i).getItemName());
            orderBackfeeItem.setItemNum(orderBackfeeItemDtoList.get(i).getItemNum());
            orderBackfeeItem.setItemPrice(orderBackfeeItemDtoList.get(i).getItemPrice());
            orderBackfeeItem.setItemAmount(orderBackfeeItemDtoList.get(i).getItemAmount());
            orderBackfeeItem.setCoId(orderBackfeeItemDtoList.get(i).getCoId());
            orderBackfeeItem.setStatus("2");
            hisOrderBackfeeItemMapper.insert(orderBackfeeItem);
        }

        return new Result<>(200, "退费成功");
    }

    @Override
    @Transactional//开启事务
    //处方退费: 微信退费
    public Result<List<OrderBackfeeItem>> updBystatus(PostObjVoV postObjVoV) {
        //获取挂号单id
        String regId = postObjVoV.getOrderBackfeeDto().getRegId();
        //获取患者姓名
        String patientName = postObjVoV.getOrderBackfeeDto().getPatientName();
        //获取总金额
        BigDecimal backAmount = postObjVoV.getOrderBackfeeDto().getBackAmount();
        //创建时间
        LocalDateTime createTime = LocalDateTime.now();
        //支付时间
        LocalDateTime bcakTime = LocalDateTime.now();
        //生成odbId
        String backId = "ODB";
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            backId += String.valueOf(random.nextInt(10));
        }

        hisOrderBackfeeMapper.insertAllzfb(backId, regId, patientName, backAmount, createTime, bcakTime);
        //获取item状态
        List<PostObjVo1> orderBackfeeItemDtoList = postObjVoV.getOrderBackfeeItemDtoList();
        for (int i = 0; i < orderBackfeeItemDtoList.size(); i++) {
            hisOrderBackfeeItemMapper.updateBystatus(orderBackfeeItemDtoList.get(i).getItemId());
        }
        for (int i = 0; i < orderBackfeeItemDtoList.size(); i++) {
            OrderBackfeeItem orderBackfeeItem = new OrderBackfeeItem();
            orderBackfeeItem.setBackId(backId);
            orderBackfeeItem.setItemId(orderBackfeeItemDtoList.get(i).getItemId());
            orderBackfeeItem.setItemType(orderBackfeeItemDtoList.get(i).getItemType());
            orderBackfeeItem.setItemName(orderBackfeeItemDtoList.get(i).getItemName());
            orderBackfeeItem.setItemNum(orderBackfeeItemDtoList.get(i).getItemNum());
            orderBackfeeItem.setItemPrice(orderBackfeeItemDtoList.get(i).getItemPrice());
            orderBackfeeItem.setItemAmount(orderBackfeeItemDtoList.get(i).getItemAmount());
            orderBackfeeItem.setCoId(orderBackfeeItemDtoList.get(i).getCoId());
            orderBackfeeItem.setStatus("2");
            hisOrderBackfeeItemMapper.insert(orderBackfeeItem);


        }
        return new Result<>(200, "退费成功");
    }
}
