package com.aaa.statistics.service.Impl;

import com.aaa.core.entity.CheckResult;
import com.aaa.core.vo.CheckResultVo;
import com.aaa.core.vo.Result;
import com.aaa.statistics.feign.CheckFeign;
import com.aaa.statistics.service.CheckService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckFeign checkFeign;

    @Override
    public Result<List<CheckResult>> selectAll(CheckResultVo checkResultVo) {
        System.out.println("checkResultVo -----------------------= " + checkResultVo);
        String checkItemId = checkResultVo.getCheckItemId();
        String patientName = checkResultVo.getPatientName();
        String dateRange1 = checkResultVo.getDateRange1();
        String dateRange2 = checkResultVo.getDateRange2();
        LocalDate dateTime= LocalDate.now();
        if (dateRange1==null&& dateRange2==null){
            dateRange1=  String.valueOf(dateTime);
            dateRange2=String.valueOf(dateTime.plusDays(1));
        }
        return checkFeign.selectAll(checkItemId,patientName,dateRange1,dateRange2);
    }

    @Override
    public Result<List<CheckResult>> selectAllTwo(CheckResultVo checkResultVo) {
        String checkItemId = checkResultVo.getCheckItemId();
        String dateRange1 = checkResultVo.getDateRange1();
        String dateRange2 = checkResultVo.getDateRange2();
        LocalDate dateTime= LocalDate.now();
        if (dateRange1==null&& dateRange2==null){
            dateRange1=  String.valueOf(dateTime);
            dateRange2=String.valueOf(dateTime.plusDays(1));
        }
        return checkFeign.selectAllTwo(checkItemId,dateRange1,dateRange2);
    }
}
