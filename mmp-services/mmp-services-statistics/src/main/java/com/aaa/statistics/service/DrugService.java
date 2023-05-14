package com.aaa.statistics.service;

import com.aaa.core.entity.CareOrderItem;

import java.util.List;
import java.util.Map;

public interface DrugService {

     List<CareOrderItem> alist(String itemName, String dateRange1, String dateRange2);

     List<Map<String, Object>> getData(String itemName, String dateRange1, String dateRange2);
}
