package com.aaa.stock.controller.api;

import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;
import com.aaa.stock.service.ProducterService;
import com.aaa.stock.vo.ProducterVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/24 11:06
 */
@RestController
@RequestMapping("stock/api/producter") //给前端提供的路径
public class ApiProducterController {

    @Autowired
    private ProducterService service;

    @PostMapping("getAll/{current}/{size}")
    public Result<IPage<Producer>> getAll(
            @PathVariable Integer current,
            @PathVariable Integer size,
            @RequestBody ProducterVo producterVo  //把JSON转Java对象
            ){
        return service.getAll(current,size,producterVo);
    }

}
