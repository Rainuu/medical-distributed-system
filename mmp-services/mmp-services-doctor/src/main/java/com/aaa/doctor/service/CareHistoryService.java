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
    Result<CareHistory> SaveHistroy(String registrationId, CareHistory careHistory);

    //根据前端传输的患者病历信息进行储存返回一个病历号码
    String  insertCareHistory(CareHistory careHistory);
    //新开就诊就诊完成载入按钮时 查询患者id的病历以就诊时间降序排获取第一条
    CareHistory queryCareHistoryId(String patientId);

 
}
