package com.aaa.stock.controller;

import com.aaa.core.entity.Medicines;
import com.aaa.core.vo.Result;
import com.aaa.stock.service.MedicinalService;
import com.aaa.stock.vo.MedicinalVo;
import com.aaa.stock.vo.NewCareVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/27 16:51
 */
@RestController
@RequestMapping("stock/api/medicinal")
public class MedicinalController {
    @Autowired
    private MedicinalService medicinalService;
    //收费管理:库存接口
    @PostMapping("num")
    public Boolean num(@RequestParam String num,@RequestParam String itemName){
        return medicinalService.num(num,itemName);
    }




}
