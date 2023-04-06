package com.aaa.doctor.service.impl;

import com.aaa.core.entity.Registration;
import com.aaa.core.vo.RegistrationVo;
import com.aaa.core.vo.Result;
import com.aaa.doctor.dao.ApiRegistrationDao;
import com.aaa.doctor.dao.RegistrationDao;
import com.aaa.doctor.service.ApiRegistrationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/31 20:33
 */
@Service
public class ApiRegistrationServiceImpl implements ApiRegistrationService {
    @Autowired
    private RegistrationDao registrationDao;

    @Override
    public Result<List<Registration>> queryByDoctor(String doctorName, String dateRange1,String dateRange2) {
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(doctorName)) {
            wrapper.like("doctor_name",doctorName);
        }
        if(StringUtils.hasText(dateRange1)&&StringUtils.hasText(dateRange2)){
            wrapper.between("create_time",dateRange1,dateRange2);
        }
        List<Registration> registrations = registrationDao.selectList(wrapper);
        return new Result<>(200,"统计查询",registrations);
    }


    /**
     * 查询每个医生的挂号总金额和每个医生的接诊数量
     * @param
     */
    @Override
    public Result<List<Registration>> ApiRegistrationSql(String doctorName, String dateRange1,String dateRange2) {
        List<Registration> registrations = registrationDao.selectSum(doctorName,dateRange1,dateRange2);
        return new Result<>(200,"统计查询",registrations);
    }
}
