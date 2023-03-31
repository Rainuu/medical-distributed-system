package com.aaa.doctor.service;

import com.aaa.core.entity.CareHistory;
import com.aaa.core.vo.Result;

import javax.smartcardio.CardTerminal;
import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/25 9:50
 */
public interface CareHistoryService {

    Result<List<CareHistory>> queryCareHistoryByPatientId(String patientId);

    List<CareHistory> getCareHistoryId(String patientId);


    /**
     * 保存病历
     */
    Result<String> SaveHistroy(CareHistory careHistory);
}
