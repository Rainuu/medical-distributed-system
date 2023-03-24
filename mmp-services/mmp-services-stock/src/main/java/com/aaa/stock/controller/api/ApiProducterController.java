package com.aaa.stock.controller.api;

import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;
import com.aaa.stock.service.ProducterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/24 11:06
 */
@RestController
@RequestMapping("stock/api/producter")
public class ApiProducterController {

    @Autowired
    private ProducterService service;

    @GetMapping("getAll")
    public Result<List<Producer>> getAll(){
        return service.getAll();
    }

}
