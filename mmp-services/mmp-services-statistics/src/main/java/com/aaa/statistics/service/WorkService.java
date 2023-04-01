package com.aaa.statistics.service;

import com.aaa.core.entity.CheckResult;
import com.aaa.core.entity.Registration;
import com.aaa.core.vo.RegistrationVo;
import com.aaa.core.vo.Result;

import java.util.List;

public interface WorkService {
    Result<List<Registration>> selectAll(RegistrationVo registrationVo);

    Result<List<Registration>> selectAllTwo(RegistrationVo registrationVo);
}
