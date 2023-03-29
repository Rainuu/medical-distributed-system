package com.aaa.doctor.service;

import com.aaa.core.entity.Patient;
import com.aaa.core.entity.RegisteredItem;
import com.aaa.core.vo.Result;
import com.aaa.doctor.vo.PatientVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/24 15:07
 */
public interface PatientService {

    Result<IPage<Patient>> findByPat(Integer currentPage, Integer pageSize, PatientVo patientVo);


    Result<Patient> patientAll(String idCard);


}
