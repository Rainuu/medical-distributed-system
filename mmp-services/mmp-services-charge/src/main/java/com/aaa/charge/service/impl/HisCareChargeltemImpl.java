package com.aaa.charge.service.impl;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.RandomUtil;
import com.aaa.charge.dao.HisCareOrderItemMapper;
import com.aaa.charge.dao.HisOrderChargeMapper;
import com.aaa.charge.service.HisCareChargeltemService;
import com.aaa.charge.vo.PostObjVo;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.entity.OrderCharge;
import com.aaa.core.entity.OrderChargeItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional//开启事务
    public Result<List<CareOrderItem>> updateBystatus(PostObjVo postObjVo) {
        //获取itemId修改状态
        for (int i=0;i<postObjVo.getOrderChargeItemDtoList().size();i++){
            hisCareOrderItemMapper.updateBystatus(postObjVo.getOrderChargeItemDtoList().get(i).getItemId());
            //详情id
            String ItemId =postObjVo.getOrderChargeItemDtoList().get(i).getItemId();
            //处方id
            String coId =postObjVo.getOrderChargeItemDtoList().get(i).getCoId();
            //项目名称
            String itemName = postObjVo.getOrderChargeItemDtoList().get(i).getItemName();
            //单价
            String itemPrice = postObjVo.getOrderChargeItemDtoList().get(i).getItemPrice();
            //数量
            String itemNum = postObjVo.getOrderChargeItemDtoList().get(i).getItemNum();
            //小计
            String itemAmount = postObjVo.getOrderChargeItemDtoList().get(i).getItemAmount();
            //类型
            String itemType = postObjVo.getOrderChargeItemDtoList().get(i).getItemType();
            hisCareOrderItemMapper.insertAll(ItemId,coId,itemName,itemPrice,itemNum,itemAmount,itemType);
        }
        //获取挂号单id
        String regId = postObjVo.getOrderChargeDto().getRegId();
        //获取患者姓名
        String patientName = postObjVo.getOrderChargeDto().getPatientName();
        //获取总金额
        Double orderAmount = postObjVo.getOrderChargeDto().getOrderAmount();
        //创建时间
        OrderCharge orderCharge = new OrderCharge();
        orderCharge.setPayTime(new DateTime());
        hisOrderChargeMapper.insertAll(regId,patientName,orderAmount,orderCharge);
        return new Result<>(200,"支付成功");
    }
}
