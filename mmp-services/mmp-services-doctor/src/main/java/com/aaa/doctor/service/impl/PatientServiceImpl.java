package com.aaa.doctor.service.impl;

import com.aaa.core.entity.Patient;
import com.aaa.core.entity.RegisteredItem;
import com.aaa.core.vo.Result;
import com.aaa.doctor.config.IDCardUtil;
import com.aaa.doctor.dao.PatientDao;
import com.aaa.doctor.service.PatientService;
import com.aaa.doctor.vo.PatientVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/24 15:09
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;

    @Override
    public Result<IPage<Patient>> findByPat(Integer currentPage, Integer pageSize, PatientVo patientVo) {
        IPage<Patient> page = new Page<>(currentPage,pageSize);
        QueryWrapper<Patient> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(patientVo.getPatientName())){
            wrapper.like("name",patientVo.getPatientName());
        }
        if (StringUtils.hasText(patientVo.getPhoneNumber())){
            wrapper.eq("phone",patientVo.getPhoneNumber());
        }
        if (Objects.nonNull(patientVo.getIdNumber())){
            wrapper.like("id_card",patientVo.getIdNumber());
        }
        IPage<Patient> page1 = patientDao.selectPage(page, wrapper);
        return new Result<IPage<Patient>>(2000,"查询患者成功",page1);

    }

    @Override
    public Result<Patient> patientAll(String idCard) {
        QueryWrapper<Patient> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(idCard)) {
            wrapper.eq("id_card",idCard);
        }
        Patient patient = patientDao.selectOne(wrapper);
        return new Result<>(2000,"查询身份信息成功",patient);
    }




}
