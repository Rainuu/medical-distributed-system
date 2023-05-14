package com.aaa.doctor.controller;

import com.aaa.core.entity.Patient;
import com.aaa.core.vo.Result;
import com.aaa.doctor.feign.PatienFeign;
import com.aaa.doctor.service.CareHistoryService;
import com.aaa.doctor.service.PatientService;
import com.aaa.doctor.vo.PatientVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/24 15:03
 */
@RestController
@RequestMapping("doctor/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private CareHistoryService careHistoryService;

    @Autowired
    private PatienFeign patienFeign;

    /*
     * 查询患者信息
     * */
    @PostMapping("searchPat/{currentPage}/{pageSize}")
    public Result<IPage<Patient>> searchPat(
            @PathVariable Integer currentPage,
            @PathVariable Integer pageSize,
            @RequestBody PatientVo patientVo
    ) {
        return patientService.findByPat(currentPage, pageSize, patientVo);

    }

    /**
     * 根据患者id进行查询患者病历
     */
    @PostMapping("queryByPatient/{patientId}")
    public Result queryByPatient(@PathVariable String patientId) {
        Patient patient = patientService.queryByPatient(patientId);
        return new Result<>(2000, "根据患者id查询患者信息成功", patient);
    }


}