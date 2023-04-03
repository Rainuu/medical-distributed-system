package com.aaa.doctor.controller;

import com.aaa.core.entity.Registration;
import com.aaa.core.entity.Scheduling;
import com.aaa.core.vo.Result;
import com.aaa.doctor.service.RegistredListService;
import com.aaa.doctor.vo.RegisteredListVo;
import com.aaa.doctor.vo.SchedulingVoo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/27 17:43
 */
@RestController
@RequestMapping("doctor/registered")
public class RegisteredListController {
//    doctor/registered/getAllList
    @Autowired
    private RegistredListService registredListService;

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


    //根据挂号列表里的registration_id修改它收费状态
    @GetMapping("updRegistrationId/{registrationId}/{status}")
    public Result updRegistrationId(@PathVariable String registrationId ,@PathVariable String status){
        System.out.println("registrationId = " + registrationId);
        System.out.println("status = " + status);
        Boolean aBoolean = registredListService.updRegistrationId(registrationId,status);
        return new Result(2000,"修改状态收费成功",aBoolean);
    }

    //根据挂号列表里的registration_id修改它退费状态
    @GetMapping("updRegistrationId2/{registrationId}/{status}")
    public Result updRegistrationId2(@PathVariable String registrationId ,@PathVariable String status){
        Boolean aBoolean = registredListService.updRegistrationId(registrationId,status);
        return new Result(2000,"修改状态退费成功",aBoolean);
    }

    //根据挂号列表里的registration_id修改它作废状态
    @GetMapping("updRegistrationId3/{registrationId}/{status}")
    public Result updRegistrationId3(@PathVariable String registrationId ,@PathVariable String status){
        Boolean aBoolean = registredListService.updRegistrationId(registrationId,status);
        return new Result(2000,"修改状态作废成功",aBoolean);
    }

}
