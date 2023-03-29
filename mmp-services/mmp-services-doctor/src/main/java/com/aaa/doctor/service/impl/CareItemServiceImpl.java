package com.aaa.doctor.service.impl;

import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.Result;
import com.aaa.doctor.service.CareItemService;
import com.aaa.doctor.dao.CareItemDao;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/25 18:09
 */
@Service
public class CareItemServiceImpl implements CareItemService {

    @Autowired
    private CareItemDao careItemDao;


    @Override
    public Result<List<CareOrderItem>> FindByItem(String patientId,String chId) {
        List<CareOrderItem> orderItem = careItemDao.getOrderItem(patientId,chId);
        return new Result<>(2000,"查询成功",orderItem);
    }

    @Override
    public List<CareOrderItem> getCareOrderItem(String coId) {
        QueryWrapper<CareOrderItem> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(coId)) {
            wrapper.eq("co_id",coId);
        }
        List<CareOrderItem> orderItem = careItemDao.selectList(wrapper);
        return orderItem;
    }
}
