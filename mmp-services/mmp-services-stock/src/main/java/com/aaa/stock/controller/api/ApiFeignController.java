package com.aaa.stock.controller.api;

import com.aaa.core.entity.DictData;
import com.aaa.core.vo.Result;
import com.aaa.stock.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/28 7:53
 */
@RestController
@RequestMapping("stock/api/feign")
public class ApiFeignController {
    @Autowired
    private FeignService feignService;

    //处理字典
    @GetMapping("status")
    public Result<List<DictData>> status() {
        return feignService.getOpenFeign();
    }
}
