package com.aaa.charge.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aaa.charge.dao.HisCareHistoryMapper;
import com.aaa.charge.dao.HisCareOrderItemMapper;
import com.aaa.charge.dao.HisCareOrderMapper;
import com.aaa.charge.service.HisCareHistoryService;
import com.aaa.core.entity.CareHistory;
import com.aaa.core.entity.CareOrder;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 病例表(HisCareHistory)表服务实现类

 */
@Service
public class HisCareHistoryServiceImpl implements HisCareHistoryService {
    @Autowired
    private HisCareHistoryMapper hisCareHistoryMapper;
    @Autowired
    private HisCareOrderMapper hisCareOrderMapper;
    @Autowired
    private HisCareOrderItemMapper hisCareOrderItemMapper;

    @Override
    //处方收费 根据regid 查询三张表数据
    public Result<Map<String, Object>> findcareHistory(String regId) {
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        wrapper.eq("reg_id", regId);
        //new CareHistory实体,获取his_care_history表一行数据
        CareHistory careHistory = hisCareHistoryMapper.selectOne(wrapper);
        if (careHistory == null) {
            return new Result<Map<String, Object>>(500, "单号错误", null);
        }
        //获取ch_id关联his_care_order表，获取co_id
        String chId = careHistory.getChId();
        QueryWrapper<CareOrder> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("ch_id", chId);
        List<CareOrder> careOrders = hisCareOrderMapper.selectList(wrapper1);
        List<Map<String, Object>> list = new ArrayList<>();
        if (careOrders.size() > 0) {
            for (CareOrder careOrder : careOrders) {
                //BeanUtil.beanToMap把对象转换为Map类型
                Map<String, Object> map1 = BeanUtil.beanToMap(careOrder);
                QueryWrapper<CareOrderItem> wrapper2 = new QueryWrapper<>();
                wrapper2.eq("status", 0);
                wrapper2.eq("co_id", careOrder.getCoId());
                List<CareOrderItem> careOrderItem = hisCareOrderItemMapper.selectList(wrapper2);
                //根据order表关联order_item表
                //计算出订单总金额
                if (careOrderItem.size() != 0) {
                    Double sum = careOrderItem.stream()
                            .map(item -> item.getNum()
                                    .multiply(item.getAmount())
                                    .doubleValue()).reduce((a, b) -> a + b).get();
                    map1.put("careOrderItem", careOrderItem);
                    map1.put("sum", sum);
                    list.add(map1);
                }

            }
        }
        //判断是否未空isEmpty()
        if (list.isEmpty()) {
            return new Result<>(402, "请输入未支付的订单", null);
        } else {
            //把数据放入map返回
            HashMap<String, Object> map = new HashMap<>();
            map.put("careHistory", careHistory);
            map.put("careOrders", list);
            return new Result<>(200, "成功", map);
        }
    }


}