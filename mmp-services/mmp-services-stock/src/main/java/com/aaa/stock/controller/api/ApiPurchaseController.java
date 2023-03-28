package com.aaa.stock.controller.api;

import com.aaa.core.entity.Purchase;
import com.aaa.core.vo.Result;
import com.aaa.stock.service.PurchaseService;
import com.aaa.stock.vo.PurchaseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/28 19:55
 */
@RestController
@RequestMapping("stock/api/purchase") //给前端提供的路径
public class ApiPurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("getAll/{current}/{size}")
    public Result<IPage<Purchase>> getAll(
            @PathVariable Integer current,
            @PathVariable Integer size,
            @RequestBody PurchaseVo purchaseVo  //传递前端用于查询的参数
    ){
        return purchaseService.getAll(current,size,purchaseVo);
    }
}
