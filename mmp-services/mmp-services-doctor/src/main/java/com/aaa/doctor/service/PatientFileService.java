package com.aaa.doctor.service;

import com.aaa.core.entity.PatientFile;
import com.aaa.core.vo.Result;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/25 17:41
 */
public interface PatientFileService {

    Result<PatientFile> getByFileId(String patientId);
}
