package com.aaa.charge.service;

import com.aaa.core.entity.CareOrderItem;

import java.util.List;
import java.util.Map;

public interface HisCareOrderItemService {
    List<CareOrderItem> getInfo(String itemName, String dateRange1, String dateRange2);

    List<Map<String, Object>> getData(String itemName, String dateRange1, String dateRange2);
}
