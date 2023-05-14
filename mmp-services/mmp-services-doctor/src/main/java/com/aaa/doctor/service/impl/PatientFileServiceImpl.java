package com.aaa.doctor.service.impl;

import com.aaa.core.entity.PatientFile;
import com.aaa.doctor.dao.PatientFileDao;
import com.aaa.doctor.service.PatientFileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/25 17:41
 */
@Service
public class PatientFileServiceImpl implements PatientFileService {

    @Autowired
    private PatientFileDao patientFileDao;

    @Override
    public PatientFile getByFileId(String patientId) {
        QueryWrapper<PatientFile> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(patientId)) {
            wrapper.eq("patient_id", patientId);
        }
        PatientFile patientFile = patientFileDao.selectOne(wrapper);
        return patientFile;
    }
}
