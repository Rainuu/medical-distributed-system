package com.aaa.statistics.service.Impl;

import com.aaa.core.entity.CareOrderItem;
import com.aaa.statistics.feign.RevenueFeign;
import com.aaa.statistics.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    private RevenueFeign revenueFeign;
    @Override
    public List<CareOrderItem> alist(String itemName, String dateRange1,String dateRange2) {
        LocalDate dateTime= LocalDate.now();
        if (dateRange1==null&& dateRange2==null){
          dateRange1=  String.valueOf(dateTime);
          dateRange2=String.valueOf(dateTime.plusDays(1));
        }
        return revenueFeign.alist(itemName,dateRange1,dateRange2);
    }

    @Override
    public List<Map<String, Object>> getData(String itemName, String dateRange1,String dateRange2) {
        LocalDate dateTime= LocalDate.now();
        if (dateRange1==null&&dateRange2==null){
            dateRange1=  String.valueOf(dateTime);
            dateRange2=String.valueOf(dateTime.plusDays(1));
        }
        return revenueFeign.getData(itemName,dateRange1,dateRange2);
    }
}
