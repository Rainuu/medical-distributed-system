package com.aaa.doctor.service.impl;

import com.aaa.core.entity.*;
import com.aaa.core.util.JwtUtil;
import com.aaa.core.util.WebUtil;
import com.aaa.core.vo.Result;
import com.aaa.doctor.dao.CareOrderDao;
import com.aaa.doctor.dao.CareOrderItemDao;
import com.aaa.doctor.feign.CheckFegin;
import com.aaa.doctor.feign.StockFegin;
import com.aaa.doctor.feign.UserFeign;
import com.aaa.doctor.service.CareOrderService;
import com.aaa.doctor.util.IdWorker;
import com.aaa.doctor.util.JsonUtil;
import com.aaa.doctor.vo.NewCareVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private CareOrderItemDao careOrderItemDao;

    @Autowired
    private CheckFegin checkFegin;

    @Autowired
    private StockFegin stockFegin;

    @Override
    public List<CareOrder> getCareOrder(String chId) {
        QueryWrapper<CareOrder> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(chId)) {
            wrapper.eq("ch_id",chId);
        }
        List<CareOrder> careOrders = careOrderDao.selectList(wrapper);
        return careOrders;
    }


    @Transactional
    @Override
    public String getCareOrders(Map<String, Object> careOrder) {
        JsonUtil<CareOrderItem> objectJsonUtil = new JsonUtil<>();
        CareOrder careOrder3 = JsonUtil.mapToObject(careOrder, CareOrder.class, "careOrder");
        List<CareOrderItem> careOrderItems2 = objectJsonUtil.mapToList(careOrder, CareOrderItem.class, "careOrderItems");

        String token = WebUtil.getRequest().getHeader("token");
        Map<String, Object> tokenData = JwtUtil.getTokenChaim(token);
        String phone = (String) tokenData.get("username");
        User user = userFeign.getByUsername(phone);

        IdWorker idWorker = new IdWorker();
        String id = "CO" + idWorker.nextId();
        int insert1=0;
        careOrder3.setCoId(id);//处方id
        careOrder3.setUserId(user.getUserId());
        careOrder3.setCreateBy(user.getUserName());
        careOrder3.setCreateTime(new Date());
        careOrder3.setUpdateBy(user.getUserName());
        careOrder3.setUpdateTime(new Date());

        int insert = careOrderDao.insert(careOrder3);
        for (CareOrderItem careOrderItem : careOrderItems2) {
            String itemId="ITEM"+idWorker.nextId();
            careOrderItem.setItemId(itemId);
            careOrderItem.setCoId(id);
            careOrderItem.setStatus("0");
            careOrderItem.setCreateTime(new Date());
            insert1 = careOrderItemDao.insert(careOrderItem);


        }

        if (insert>0&&insert1>0){
            return careOrder3.getChId();
        }


        return null;
    }

    @Override
    public List<CareOrder> queryCoId(String chId) {

        if (StringUtils.hasText(chId)){
            QueryWrapper<CareOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("ch_id",chId);
            List<CareOrder> careOrders= careOrderDao.selectList(wrapper);
            for (CareOrder careOrder : careOrders) {
                QueryWrapper<CareOrderItem> wrapper1 = new QueryWrapper<>();
                if (StringUtils.hasText(careOrder.getCoId())){
                    wrapper1.eq("co_id",careOrder.getCoId());
                    List<CareOrderItem> careOrderItems = careOrderItemDao.selectList(wrapper1);
                    careOrder.setCareOrderItemList(careOrderItems);
                }

            }
            return  careOrders;
        }else {
            return null;
        }

    }

    @Override
    public Result<Page<Medicines>> getStockMedicines(NewCareVo newCareVo) {
        Result<Page<Medicines>> pageResult = stockFegin.MeDicinesController(newCareVo);
        return pageResult;

    }

    @Override
    public Page<CheckItem> getCheckItem(NewCareVo newCareVo) {
        Page<CheckItem> checkItemPage = checkFegin.queryCheckItem(newCareVo);
        return checkItemPage;
    }
}
