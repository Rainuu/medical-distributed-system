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
        return workFeign.queryByDoctor(registrationVo);
    }

    @Override
    public Result<List<Registration>> selectAllTwo(RegistrationVo registrationVo) {
        return workFeign.queryByDoctor2(registrationVo);
    }
}
