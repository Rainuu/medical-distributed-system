package com.aaa.doctor.controller;

import com.aaa.core.entity.PatientFile;
import com.aaa.core.vo.Result;
import com.aaa.doctor.service.PatientFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/25 17:38
 */
@RestController
@RequestMapping("doctor/patient")
public class PatientFileController {

    @Autowired
    private PatientFileService patientFileService;

    @GetMapping("getByFileId/{patientId}")
    public Result<PatientFile> getByFileId(@PathVariable String patientId) {
        return patientFileService.getByFileId(patientId);
    }
}
