package com.aaa.statistics.service.Impl;

import com.aaa.core.entity.Registration;
import com.aaa.core.vo.RegistrationVo;
import com.aaa.core.vo.Result;
import com.aaa.statistics.feign.WorkFeign;
import com.aaa.statistics.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkFeign workFeign;

    @Override
    public Result<List<Registration>> selectAll(RegistrationVo registrationVo) {
        String doctorName = registrationVo.getDoctorName();
        String dateRange1 = registrationVo.getDateRange1();
        String dateRange2 = registrationVo.getDateRange2();
        LocalDate dateTime= LocalDate.now();
        if (dateRange1==null&& dateRange2==null){
            dateRange1=  String.valueOf(dateTime);
            dateRange2=String.valueOf(dateTime.plusDays(1));
        }
        return workFeign.queryByDoctor(doctorName,dateRange1,dateRange2);
    }

    @Override
    public Result<List<Registration>> selectAllTwo(RegistrationVo registrationVo) {
        String doctorName = registrationVo.getDoctorName();
        String dateRange1 = registrationVo.getDateRange1();
        String dateRange2 = registrationVo.getDateRange2();
        LocalDate dateTime= LocalDate.now();
        if (dateRange1==null&& dateRange2==null){
            dateRange1=  String.valueOf(dateTime);
            dateRange2=String.valueOf(dateTime.plusDays(1));
        }
        return workFeign.queryByDoctor2(doctorName,dateRange1,dateRange2);
    }
}
