package com.aaa.doctor.controller;

import com.aaa.core.entity.Registration;
import com.aaa.core.vo.Result;
import com.aaa.doctor.service.RegistredListService;
import com.aaa.doctor.vo.RegisteredListVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mysql.cj.Constants;
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
    * 修改挂号状态
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

}
