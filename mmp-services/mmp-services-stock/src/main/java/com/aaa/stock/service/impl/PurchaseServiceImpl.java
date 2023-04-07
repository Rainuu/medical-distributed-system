package com.aaa.stock.service.impl;

import com.aaa.core.entity.InventoryLog;
import com.aaa.core.entity.Purchase;
import com.aaa.core.entity.PurchaseItem;
import com.aaa.core.entity.User;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.stock.dao.InventoryLogDao;
import com.aaa.stock.dao.PurchaseDao;
import com.aaa.stock.dao.PurchaseItemDao;
import com.aaa.stock.feign.Feign;
import com.aaa.stock.service.MedicinalService;
import com.aaa.stock.service.PurchaseService;
import com.aaa.stock.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

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

    @Autowired
    private PurchaseItemDao purchaseItemDao;

    @Autowired
    private InventoryLogDao inventoryLogDao;

    @Autowired
    private Feign feign;

    @Autowired
    private MedicinalService medicinalService;

    @Override
    public Result<IPage<Purchase>> getAll(Integer current, Integer size, PurchaseVo purchaseVo) {
        IPage<Purchase> page = new Page<>(current, size); //分页对象
        QueryWrapper<Purchase> wrapper = new QueryWrapper<>();  //条件构造器
        if (Objects.nonNull(purchaseVo.getProviderId())){
            wrapper.like("provider_id",purchaseVo.getProviderId());
        }
        if (Objects.nonNull(purchaseVo.getApplyUserName())){
            wrapper.like("apply_user_name",purchaseVo.getApplyUserName());
        }
        if (Objects.nonNull(purchaseVo.getStatus())){
            wrapper.eq("status",purchaseVo.getStatus());
        }
        IPage<Purchase> page1 = purchaseDao.selectPage(page, wrapper);
        return new Result<IPage<Purchase>>(200,"查询成功",page1);
    }

    // 分页查询所有的待审核采购单数据
    @Override
    public Result<IPage<Purchase>> listPurchasePendingForPage(Integer current, Integer size, PurchaseVo purchaseVo) {
        IPage<Purchase> page = new Page<>(current, size); //分页对象
        IPage<Purchase> page1 = purchaseDao.listPurchasePendingForPage(page,purchaseVo);
        return new Result<IPage<Purchase>>(200,"查询成功",page1);
    }

    @Override
    public void auditPass(String purchaseId) {
        purchaseDao.auditPass(purchaseId);
    }

    @Override
    public void auditNoPass(String purchaseId, String value) {
        purchaseDao.auditNoPass(purchaseId,value);
    }

    @Override
    public void insertByPurchaseId(Purchase purchase) {
        purchaseDao.insertByPurchaseId(purchase);
    }

    @Override
    @Transactional
    public Result<List<PurchaseItem>> addPurchase(PurchaseAllVo purchaseAllVo) {
        // 单插
        String purchaseId = purchaseAllVo.getPurchaseDto().getPurchaseId();
        String providerId = purchaseAllVo.getPurchaseDto().getProviderId();
        BigDecimal purchaseTradeTotalAmount = purchaseAllVo.getPurchaseDto().getPurchaseTradeTotalAmount();

        HttpServletRequest request = WebUtil.getRequest();
        String token = request.getHeader("token");
        Map<String, Object> info = JwtUtil.getTokenChaim(token);
        String phone = (String) info.get("username");
        User byUsername = feign.getByUsername(phone);
        String userName = byUsername.getUserName();

        Purchase purchase = new Purchase();

        purchase.setPurchaseId(purchaseId);
        purchase.setProviderId(providerId);
        purchase.setPurchaseTradeTotalAmount(purchaseTradeTotalAmount);
        purchase.setStatus("1");
        purchase.setCreateBy(userName);
        purchase.setUpdateBy(userName);
        purchase.setCreateTime(new Date());
        purchase.setCreateBy(userName);
        purchase.setUpdateBy(userName);
        purchase.setApplyUserName(userName);
        // purchase.setExamine();

        purchaseDao.insert(purchase);


        List<PurchaseItemDtosVo> purchaseItemDtosVos = purchaseAllVo.getPurchaseItemDtos();
        for (int i = 0; i < purchaseItemDtosVos.size(); i++) {
            String medicinesId = purchaseAllVo.getPurchaseItemDtos().get(i).getMedicinesId().toString();
            String medicinesName = purchaseAllVo.getPurchaseItemDtos().get(i).getMedicinesName();
            Integer purchaseNumber = purchaseAllVo.getPurchaseItemDtos().get(i).getPurchaseNumber();
            String medicinesType = purchaseAllVo.getPurchaseItemDtos().get(i).getMedicinesType();
            String prescriptionType = purchaseAllVo.getPurchaseItemDtos().get(i).getPrescriptionType();
            String unit = purchaseAllVo.getPurchaseItemDtos().get(i).getUnit();
            Integer conversion = purchaseAllVo.getPurchaseItemDtos().get(i).getConversion();
            String keywords = purchaseAllVo.getPurchaseItemDtos().get(i).getKeywords();
            BigDecimal tradePrice = purchaseAllVo.getPurchaseItemDtos().get(i).getTradePrice();
            BigDecimal tradeTotalAmount = purchaseAllVo.getPurchaseItemDtos().get(i).getTradeTotalAmount();
            String batchNumber = purchaseAllVo.getPurchaseItemDtos().get(i).getBatchNumber();
            String remark = purchaseAllVo.getPurchaseItemDtos().get(i).getRemark();

            PurchaseItem purchaseItem = new PurchaseItem();

            purchaseItem.setPurchaseId(purchaseId);
            purchaseItem.setMedicinesId(medicinesId);
            purchaseItem.setPurchaseNumber(purchaseNumber);
            purchaseItem.setTradePrice(tradePrice);
            purchaseItem.setTradeTotalAmount(tradeTotalAmount);
            purchaseItem.setBatchNumber(batchNumber);
            purchaseItem.setRemark(remark);
            purchaseItem.setMedicinesName(medicinesName);
            purchaseItem.setMedicinesType(medicinesType);
            purchaseItem.setPrescriptionType(prescriptionType);
            purchaseItem.setPurchaseId(purchaseId);
            purchaseItem.setConversion(conversion);
            purchaseItem.setUnit(unit);
            purchaseItem.setKeywords(keywords);

            purchaseItemDao.insert(purchaseItem);
        }
        return new Result<>(200,"成功");
    }

    @Override
    public Result<List<PurchaseItem>> addPurchaseToAudit(PurchaseAllVo purchaseAllVo) {
        // 单插
        String purchaseId = purchaseAllVo.getPurchaseDto().getPurchaseId();
        String providerId = purchaseAllVo.getPurchaseDto().getProviderId();
        BigDecimal purchaseTradeTotalAmount = purchaseAllVo.getPurchaseDto().getPurchaseTradeTotalAmount();

        HttpServletRequest request = WebUtil.getRequest();
        String token = request.getHeader("token");
        Map<String, Object> info = JwtUtil.getTokenChaim(token);
        String phone = (String) info.get("username");
        User byUsername = feign.getByUsername(phone);
        String userName = byUsername.getUserName();
        // LocalDateTime localDateTime = LocalDateTime.now(); // 当前日期和时间】
        Date date = new Date();
        Purchase purchase = new Purchase();

        purchase.setPurchaseId(purchaseId);
        purchase.setProviderId(providerId);
        purchase.setPurchaseTradeTotalAmount(purchaseTradeTotalAmount);
        purchase.setStatus("2");
        purchase.setCreateBy(userName);
        purchase.setUpdateBy(userName);
        purchase.setCreateTime(new Date());
        purchase.setApplyUserName(userName);
        // purchase.setExamine();
        purchaseDao.insert(purchase);


        List<PurchaseItemDtosVo> purchaseItemDtosVos = purchaseAllVo.getPurchaseItemDtos();
        for (int i = 0; i < purchaseItemDtosVos.size(); i++) {
            String medicinesId = purchaseAllVo.getPurchaseItemDtos().get(i).getMedicinesId().toString();
            String medicinesName = purchaseAllVo.getPurchaseItemDtos().get(i).getMedicinesName();
            Integer purchaseNumber = purchaseAllVo.getPurchaseItemDtos().get(i).getPurchaseNumber();
            String medicinesType = purchaseAllVo.getPurchaseItemDtos().get(i).getMedicinesType();
            String prescriptionType = purchaseAllVo.getPurchaseItemDtos().get(i).getPrescriptionType();
            String unit = purchaseAllVo.getPurchaseItemDtos().get(i).getUnit();
            String producterId = purchaseAllVo.getPurchaseItemDtos().get(i).getProducterId();
            Integer conversion = purchaseAllVo.getPurchaseItemDtos().get(i).getConversion();
            String keywords = purchaseAllVo.getPurchaseItemDtos().get(i).getKeywords();
            BigDecimal tradePrice = purchaseAllVo.getPurchaseItemDtos().get(i).getTradePrice();
            BigDecimal tradeTotalAmount = purchaseAllVo.getPurchaseItemDtos().get(i).getTradeTotalAmount();
            String batchNumber = purchaseAllVo.getPurchaseItemDtos().get(i).getBatchNumber();
            String remark = purchaseAllVo.getPurchaseItemDtos().get(i).getRemark();

            PurchaseItem purchaseItem = new PurchaseItem();

            purchaseItem.setProducterId(producterId);
            purchaseItem.setPurchaseId(purchaseId);
            purchaseItem.setMedicinesId(medicinesId);
            purchaseItem.setPurchaseNumber(purchaseNumber);
            purchaseItem.setTradePrice(tradePrice);
            purchaseItem.setTradeTotalAmount(tradeTotalAmount);
            purchaseItem.setBatchNumber(batchNumber);
            purchaseItem.setRemark(remark);
            purchaseItem.setMedicinesName(medicinesName);
            purchaseItem.setMedicinesType(medicinesType);
            purchaseItem.setPrescriptionType(prescriptionType);
            purchaseItem.setPurchaseId(purchaseId);
            purchaseItem.setConversion(conversion);
            purchaseItem.setUnit(unit);
            purchaseItem.setKeywords(keywords);
            purchaseItemDao.insert(purchaseItem);
        }
        return new Result<>(200,"成功");
    }

    @Override
    public void doInvalid(String purchaseId) {
        purchaseDao.updateByPurchaseId(purchaseId);
    }

    @Override
    public void doAudit(String purchaseId) {
        purchaseDao.doAudit(purchaseId);
    }

    @Override
    public void doInventory(String purchaseId) {

        // 修改状态
        purchaseDao.RuKuUpd(purchaseId);

        // 添加入库操作人、入库时间
        HttpServletRequest request = WebUtil.getRequest();
        String token = request.getHeader("token");
        Map<String, Object> info = JwtUtil.getTokenChaim(token);
        String phone = (String) info.get("username");
        User byUsername = feign.getByUsername(phone);
        String userName = byUsername.getUserName();

        // 直接用实体，传通用的
        Purchase purchase = new Purchase();
        purchase.setStorageOptTime(new Date());
        purchase.setPurchaseId(purchaseId);
        purchase.setStorageOptUser(userName);

        purchaseDao.updateById(purchase);

        // 获取ID相同的订单
        List<PurchaseItem> byPurchaseId = purchaseItemDao.getByPurchaseId(purchaseId);
         System.out.println("byPurchaseId = " + byPurchaseId);
        for (int i = 0; i < byPurchaseId.size(); i++) {
            String itemId = byPurchaseId.get(i).getItemId();
            String purchaseId1 = byPurchaseId.get(i).getPurchaseId();
            String medicinesId = byPurchaseId.get(i).getMedicinesId();
            Integer purchaseNumber = byPurchaseId.get(i).getPurchaseNumber();
            BigDecimal tradePrice = byPurchaseId.get(i).getTradePrice();
            BigDecimal tradeTotalAmount = byPurchaseId.get(i).getTradeTotalAmount();
            String batchNumber = byPurchaseId.get(i).getBatchNumber();
            String remark = byPurchaseId.get(i).getRemark();
            String medicinesName = byPurchaseId.get(i).getMedicinesName();
            String medicinesType = byPurchaseId.get(i).getMedicinesType();
            String prescriptionType = byPurchaseId.get(i).getPrescriptionType();
            String producterId = byPurchaseId.get(i).getProducterId();
            Integer conversion = byPurchaseId.get(i).getConversion();
            String unit = byPurchaseId.get(i).getUnit();
            String keywords = byPurchaseId.get(i).getKeywords();

            InventoryLog inventoryLog = new InventoryLog();

            inventoryLog.setInventoryLogId(itemId);
            inventoryLog.setPurchaseId(purchaseId1);
            inventoryLog.setMedicinesId(medicinesId);
            inventoryLog.setInventoryLogNum(purchaseNumber);
            inventoryLog.setTradePrice(tradePrice);
            //inventoryLog.setPrescriptionPrice(tradePrice);
            inventoryLog.setTradeTotalAmount(tradeTotalAmount);
            //inventoryLog.setPrescriptionTotalAmount(tradeTotalAmount);
            inventoryLog.setBatchNumber(batchNumber);
            inventoryLog.setMedicinesName(medicinesName);
            inventoryLog.setMedicinesType(medicinesType);
            inventoryLog.setPrescriptionType(prescriptionType);
            inventoryLog.setProducterId(producterId);
            inventoryLog.setConversion(conversion);
            inventoryLog.setUnit(unit);
            inventoryLog.setCreateTime(new Date());
            //inventoryLog.setProviderId(producterId);
            inventoryLogDao.insert(inventoryLog);
            System.out.println("--------------------------------");

            InventoryLog inventoryLog2 = new InventoryLog();

            // 修改入库
            medicinalService.AddNum(inventoryLog.getInventoryLogNum().toString(),inventoryLog.getMedicinesName());

        }
    }

}
