package com.aaa.doctor.service.impl;

import com.aaa.core.entity.CareOrder;
import com.aaa.doctor.dao.CareOrderDao;
import com.aaa.doctor.service.CareOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/26 19:48
 */
@Service
public class CareOrderServiceImpl implements CareOrderService {

    @Autowired
    private CareOrderDao careOrderDao;

    @Override
    public List<CareOrder> getCareOrder(String chId) {
        QueryWrapper<CareOrder> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(chId)) {
            wrapper.eq("ch_id",chId);
        }
        List<CareOrder> careOrders = careOrderDao.selectList(wrapper);
        return careOrders;
    }
}
