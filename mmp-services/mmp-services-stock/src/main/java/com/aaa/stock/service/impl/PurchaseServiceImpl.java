package com.aaa.stock.service.impl;

import com.aaa.core.entity.Provider;
import com.aaa.core.entity.Purchase;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.PurchaseDao;
import com.aaa.stock.service.PurchaseService;
import com.aaa.stock.vo.PurchaseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/28 19:53
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, Purchase> implements PurchaseService {
    @Autowired
    private PurchaseDao purchaseDao;

    @Override
    public Result<IPage<Purchase>> getAll(Integer current, Integer size, PurchaseVo purchaseVo) {
        IPage<Purchase> page = new Page<>(current, size); //分页对象
        QueryWrapper<Purchase> wrapper = new QueryWrapper<>();  //条件构造器
        if (Objects.nonNull(purchaseVo.getProviderId())){
            wrapper.like("provider_id",purchaseVo.getProviderId());
        }
        if (Objects.nonNull(purchaseVo.getApplyUserId())){
            wrapper.like("apply_user_id",purchaseVo.getApplyUserId());
        }
        if (Objects.nonNull(purchaseVo.getStatus())){
            wrapper.eq("status",purchaseVo.getStatus());
        }
        IPage<Purchase> page1 = purchaseDao.selectPage(page, wrapper);
        return new Result<IPage<Purchase>>(200,"查询成功",page1);
    }
}
