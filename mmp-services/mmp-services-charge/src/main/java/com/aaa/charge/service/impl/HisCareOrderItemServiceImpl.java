package com.aaa.charge.service.impl;

import com.aaa.charge.dao.HisCareOrderItemMapper;
import com.aaa.charge.service.HisCareOrderItemService;
import com.aaa.core.entity.CareOrderItem;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *   药品销售统计接口
 */
@Service
public class HisCareOrderItemServiceImpl implements HisCareOrderItemService {
    @Autowired
    private HisCareOrderItemMapper mapper;

    @Override
    //统计
    public List<CareOrderItem> getInfo(String itemName, String dateRange1, String dateRange2) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (itemName != null && itemName != "") {
            queryWrapper.like("item_name", itemName);
        }
        queryWrapper.eq("item_type", 0);
        queryWrapper.between("create_time", dateRange1, dateRange2);


        return mapper.selectList(queryWrapper);
    }

    @Override
    //统计
    public List<Map<String, Object>> getData(String itemName, String dateRange1, String dateRange2) {
        return mapper.getData(itemName, dateRange1, dateRange2);
    }
}
