package com.aaa.doctor.service.impl;

import com.aaa.core.entity.Registration;
import com.aaa.core.vo.Result;
import com.aaa.doctor.dao.RegistredListDao;
import com.aaa.doctor.service.RegistredListService;
import com.aaa.doctor.vo.RegisteredListVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Objects;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/27 11:35
 */
@Service
public class RegistredListServiceImpl implements RegistredListService {

    @Autowired
    private RegistredListDao registredListDao;

    @Override
    public Result<IPage<Registration>> getAllList(Integer currentPage, Integer pageSize,RegisteredListVo registeredListVo) {
        IPage<Registration> page = new Page<>(currentPage,pageSize);
        QueryWrapper<Registration> wrapper = new QueryWrapper<>();
        // 部门id
        if (Objects.nonNull(registeredListVo.getDeptId())) {
            wrapper.eq("dept_id ",registeredListVo.getDeptId());
        }
        // 患者姓名
        if (StringUtils.hasText(registeredListVo.getPatientName())) {
            wrapper.like("patient_name",registeredListVo.getPatientName());
        }
        // 挂号类型
        if (Objects.nonNull(registeredListVo.getType())) {
            wrapper.eq("scheduling_type",registeredListVo.getType());
        }
        // 挂号时段
        if (Objects.nonNull(registeredListVo.getTimeInterval())) {
            wrapper.eq("subsection_type",registeredListVo.getTimeInterval());
        }
        // 挂号状态
        if (Objects.nonNull(registeredListVo.getStatus())) {
            wrapper.eq("registration_status",registeredListVo.getStatus());
        }
        // 部门id
        if (Objects.nonNull(registeredListVo.getTime())) {
            wrapper.eq("create_time",registeredListVo.getTime());
        }
        IPage<Registration> page1 = registredListDao.selectPage(page,wrapper);
        return new Result<IPage<Registration>>(2000,"查询患者信息成功",page1);
    }


    /*
    * 修改挂号列表状态
    * */
    @Override
    public Result<Integer> handleSuccess(String registrationId) {
        Integer integerResult = registredListDao.handleSuccess(registrationId);
        return new Result<Integer>(2000,"收费成功",integerResult);
    }

    @Override
    public Result<Integer> handleReturn(String registrationId) {
        Integer integerResult = registredListDao.handleReturn(registrationId);
        return new Result<Integer>(2000,"退号成功",integerResult);
    }

    @Override
    public Result<Integer> handleFalse(String registrationId) {
        Integer integerResult = registredListDao.handleFalse(registrationId);
        return new Result<Integer>(2000,"作废成功",integerResult);
    }


}
