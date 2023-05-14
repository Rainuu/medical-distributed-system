package com.aaa.doctor.service;

import com.aaa.core.entity.CareOrder;
import com.aaa.core.entity.CheckItem;
import com.aaa.core.entity.Medicines;
import com.aaa.core.vo.Result;
import com.aaa.doctor.vo.NewCareVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/26 19:46
 */
public interface CareOrderService {
    List<CareOrder> getCareOrder(String chId);


    String getCareOrders(Map<String, Object> careOrder);

    List<CareOrder> queryCoId(String chId);

    Result<Page<Medicines>> getStockMedicines(NewCareVo newCareVo);

    Page<CheckItem> getCheckItem(NewCareVo newCareVo);
}
