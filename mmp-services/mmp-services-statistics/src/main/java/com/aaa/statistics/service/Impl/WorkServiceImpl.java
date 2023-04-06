package com.aaa.statistics.service.Impl;

import com.aaa.core.entity.Registration;
import com.aaa.core.vo.RegistrationVo;
import com.aaa.core.vo.Result;
import com.aaa.statistics.feign.WorkFeign;
import com.aaa.statistics.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return workFeign.queryByDoctor(doctorName,dateRange1,doctorName);
    }

    @Override
    public Result<List<Registration>> selectAllTwo(RegistrationVo registrationVo) {
        String doctorName = registrationVo.getDoctorName();
        String dateRange1 = registrationVo.getDateRange1();
        String dateRange2 = registrationVo.getDateRange2();
        return workFeign.queryByDoctor2(doctorName,dateRange1,doctorName);
    }
}
