package com.aaa.doctor.controller;

import com.aaa.core.entity.CareHistory;
import com.aaa.core.entity.CheckItem;
import com.aaa.core.vo.Result;
import com.aaa.doctor.service.CareHistoryService;
import com.aaa.doctor.service.CareOrderService;
import com.aaa.doctor.service.RegistredListService;
import com.aaa.doctor.vo.NewCareVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/4/1 10:17
 */
@RestController
@RequestMapping("doctor/newcare")
public class NewCareController {

    @Autowired
    private CareHistoryService careHistoryService;

    @Autowired
    private CareOrderService careOrderService;

    @Autowired
    private RegistredListService registredListService;

    //根据前端传输的患者病历信息进行储存返回一个病历号码
//    @PostMapping("insertCareHistory")
//    public Result insertCareHistory(@RequestBody CareHistory careHistory){
//        System.out.println(careHistory);
//        String s = careHistoryService.insertCareHistory(careHistory);
//        return new Result(2000,"添加成功返回病历",s);
//    }

    //根据前端传输的患者病历信息进行储存返回一个病历号码
    @PostMapping("insertCareHistory")
    public Result insertCareHistory(@RequestBody CareHistory careHistory){
        System.out.println(careHistory);
        String s = careHistoryService.insertCareHistory(careHistory);
        return new Result(2000,"添加成功返回病历",s);
    }

//    @PostMapping("queryCareHistoryId/{patientId}")
//    public Result queryCareHistoryId(@PathVariable String patientId){
//        CareHistory careHistory = careHistoryService.queryCareHistoryId(patientId);
//        return new Result<>(2000,"根据患者id查询名下的所有关联数据成功",careHistory);
//    }
//新开就诊就诊完成载入按钮时 查询挂号单号的病历以就诊时间降序排获取第一条
@PostMapping("queryCareHistoryId/{registrationId}")
public Result queryCareHistoryId(@PathVariable String registrationId){
    CareHistory careHistory = careHistoryService.queryCareHistoryId(registrationId);
    return new Result(2000,"根据患者id查询名下的所有关联数据成功",careHistory);
}

    //远程调用获取Stock模块里面的药品信息
    @PostMapping("getStockMedicines")
    public Result getStockMedicines(@RequestBody NewCareVo newCareVo){
        return careOrderService.getStockMedicines(newCareVo);
    }

    //远程调用获取Stock模块里面的检测项目信息
    @PostMapping("getCheckItem")
    public Result getCheckItem(@RequestBody NewCareVo newCareVo){
        Page<CheckItem> checkItem = careOrderService.getCheckItem(newCareVo);
        return new Result(2000,"查询检测项目表成功",checkItem);
    }

    //根据前端传输过来的病历id查看病历是否存在 存在true 不存在 false
    @PostMapping("queryCareHistoryById/{chId}")
    public Result queryCareHistoryById(@PathVariable String chId){
        Boolean aBoolean = careHistoryService.queryCareHistoryById(chId);
        return new Result(2000,"根据病历id查询是否存在成功",aBoolean);
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
