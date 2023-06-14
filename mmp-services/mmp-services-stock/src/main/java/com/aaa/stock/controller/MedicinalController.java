package com.aaa.stock.controller;

import com.aaa.stock.service.MedicinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@RestController
@RequestMapping("stock/api/medicinal")
public class MedicinalController {
    @Autowired
    private MedicinalService medicinalService;

    //收费管理:库存接口
    @PostMapping("num")
    public Boolean num(@RequestParam String num, @RequestParam String itemName) {
        return medicinalService.num(num, itemName);
    }


}
