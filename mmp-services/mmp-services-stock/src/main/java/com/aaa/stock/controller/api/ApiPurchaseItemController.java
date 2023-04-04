package com.aaa.stock.controller.api;

import com.aaa.core.entity.PurchaseItem;
import com.aaa.core.vo.Result;
import com.aaa.stock.service.PurchaseItemService;
import com.aaa.stock.service.PurchaseService;
import com.aaa.stock.vo.MedicinalVo;
import com.aaa.stock.vo.PurchaseItemVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/4/3 8:05
 */
@RestController
@RequestMapping("stock/api/purchaseItem") //给前端提供的路径
public class ApiPurchaseItemController {

    @Autowired
    private PurchaseItemService purchaseItemService;

    // 查看详情
    @GetMapping("getPurchaseItemById/{purchaseId}")
    public Result<List<PurchaseItem>> getPurchaseItemById(@PathVariable String purchaseId){
        return purchaseItemService.getPurchaseItemById(purchaseId);
    }

    // 查询&分页
    @PostMapping("getAllById/{current}/{size}/{purchaseId}")
    public Result<IPage<List<PurchaseItem>>> getAllById(
            @PathVariable Integer current,
            @PathVariable Integer size,
            @PathVariable String purchaseId
    ){
        return purchaseItemService.getAllById(current,size,purchaseId);
    }
}
