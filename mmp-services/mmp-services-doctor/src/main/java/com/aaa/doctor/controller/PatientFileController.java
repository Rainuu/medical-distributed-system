package com.aaa.doctor.controller;

import com.aaa.core.entity.PatientFile;
import com.aaa.core.vo.Result;
import com.aaa.doctor.service.PatientFileService;
import com.aaa.doctor.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("getByFileId/{patientId}")
    public Result<PatientFile> getByFileId(@PathVariable String patientId) {
        return patientFileService.getByFileId(patientId);
    }


    /**
     * 挂号列表中----》进行添加挂号
     */
    @PostMapping("storage/{userId}/{regItemAmount}/{regItemid}")
    public Result insertRegistration(@PathVariable String userId,
                                     @PathVariable String regItemAmount,
                                     @PathVariable Long regItemId,
                                     @RequestBody Map<String,Object> obj){
        Boolean aBoolean = registrationService.insertRegistration(userId, regItemAmount, regItemId, obj);
        return new Result<>(2000,"添加挂号成功",aBoolean);
    }

    // 根据部门号查询编号最大的编号
    @PostMapping("getRegNumber")
    public Integer getRegNumber(Long dept) {
        Integer ragNumber = registrationService.getRagNumber(dept);
        return ragNumber;
    }

}
