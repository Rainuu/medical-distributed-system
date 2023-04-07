package com.aaa.doctor.controller;

import com.aaa.core.entity.Patient;
import com.aaa.core.entity.RegisteredItem;
import com.aaa.core.entity.Registration;
import com.aaa.core.entity.Scheduling;
import com.aaa.core.vo.Result;
import com.aaa.doctor.feign.PatienFeign;
import com.aaa.doctor.service.PatientService;
import com.aaa.doctor.service.RegistredListService;
import com.aaa.doctor.vo.RegisteredListVo;
import com.aaa.doctor.vo.SchedulingVoo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/27 17:43
 */
@RestController
@RequestMapping("doctor/registered")
public class RegisteredListController {

    @Autowired
    private RegistredListService registredListService;

    @Autowired
    private PatienFeign patienFeign;

    @Autowired
    private PatientService patientService;


    /*
    * 查询出挂号列表数据并进行分页
    * */
    @PostMapping("/getAllList/{currentPage}/{pageSize}")
    public Result<IPage<Registration>> getAllList(
            @PathVariable Integer currentPage,
            @PathVariable Integer pageSize,
            @RequestBody RegisteredListVo registeredListVo
    ) {
        return registredListService.getAllList(currentPage,pageSize,registeredListVo);

    }

    /*
    * 修改挂号状态  收费、退号、作废
    * */
    @PostMapping("/handleSuccess/{registrationId}")
    public Result<Integer> handleSuccess (@PathVariable String registrationId) {
        return registredListService.handleSuccess(registrationId);

    }

    @PostMapping("/handleReturn/{registrationId}")
    public Result<Integer> handleReturn (@PathVariable String registrationId) {
        return registredListService.handleReturn(registrationId);

    }

    @PostMapping("/handleFalse/{registrationId}")
    public Result<Integer> handleFalse (@PathVariable String registrationId) {
        return registredListService.handleFalse(registrationId);

    }

    /**
     * 查询医生排班
     */
    @PostMapping("findDoctocList/{currentPage}/{pageSize}")
    public Result<IPage<Scheduling>> findDoctocList(
            @PathVariable Integer currentPage,
            @PathVariable Integer pageSize,
            @RequestBody SchedulingVoo schedulingVoo) {
        return registredListService.findDoctocList(currentPage,pageSize,schedulingVoo);
    }

    /*
     * 门诊挂号下的四个动态按钮
     * */
    @GetMapping("registeredItem")
    public Result registeredItem() {
        List<RegisteredItem> registeredItems = patienFeign.getAll();
        return new Result(2000,"查询动态数组成功",registeredItems);
    }

    /*
     * 门诊挂号查询身份信息
     *  加载身份证信息
     * */
    @PostMapping("patientAll/{idCard}")
    public Result<Patient> patientAll(@PathVariable String idCard) {
        return patientService.patientAll(idCard);
    }



}
