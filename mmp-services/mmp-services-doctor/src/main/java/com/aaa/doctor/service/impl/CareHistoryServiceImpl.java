package com.aaa.doctor.service.impl;

import com.aaa.core.entity.CareHistory;
import com.aaa.core.vo.Result;
import com.aaa.doctor.dao.CareHistoryDao;
import com.aaa.doctor.service.CareHistoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.smartcardio.CardTerminal;
import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/25 10:20
 */
@Service
public class CareHistoryServiceImpl implements CareHistoryService {

    @Autowired
    private CareHistoryDao careHistoryDao;

    @Override
    public Result<List<CareHistory>> queryCareHistoryByPatientId(String patientId) {
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(patientId)) {
            wrapper.eq("patient_id",patientId);
        }
      List<CareHistory> historyList =  careHistoryDao.selectAll(patientId);

        return new Result(2000,"查询患者病历成功",historyList);
    }

    @Override
    public List<CareHistory> getCareHistoryId(String patientId) {
        QueryWrapper<CareHistory> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(patientId)) {
            wrapper.eq("patient_id",patientId);
        }
        List<CareHistory> careHistoryList = careHistoryDao.selectList(wrapper);
        return careHistoryList;
    }


}
