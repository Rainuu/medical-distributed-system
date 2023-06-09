package com.aaa.stock.controller.api;

import com.aaa.core.entity.Purchase;
import com.aaa.core.entity.PurchaseItem;
import com.aaa.core.vo.Result;
import com.aaa.stock.service.PurchaseService;
import com.aaa.stock.vo.PurchaseAllVo;
import com.aaa.stock.vo.PurchaseVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@RestController
@RequestMapping("stock/api/purchase") //给前端提供的路径
public class ApiPurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    // 查询&分页&模糊————采购入库主列表
    @PostMapping("getAll/{current}/{size}")
    public Result<IPage<Purchase>> getAll(
            @PathVariable Integer current,
            @PathVariable Integer size,
            @RequestBody PurchaseVo purchaseVo  //传递前端用于查询的参数
    ) {
        return purchaseService.getAll(current, size, purchaseVo);
    }

    // 生成purchaseId
    @GetMapping("generatePurchaseId")
    public Result<String> generatePurchaseId() {
        String purchaseId = "CG";
        Random random = new Random();
        for (int i = 0; i < 19; i++) {
            purchaseId += String.valueOf(random.nextInt(10));
        }
        // System.out.println("================="+purchaseId+"=======================");
        return new Result<>(200, "单据号生成成功", purchaseId);
    }

    // ---------------------------------新增库存------------------------------------------------

    // 添加 & 暂存[未提交] status=1
    @PostMapping("addPurchase")
    public Result<List<PurchaseItem>> addPurchase(@RequestBody PurchaseAllVo purchaseAllVo) {
        return purchaseService.addPurchase(purchaseAllVo);
    }

    // 添加 & 提交审核[待审核] status=2
    @PostMapping("addPurchaseToAudit")
    public Result<List<PurchaseItem>> addPurchaseToAudit(@RequestBody PurchaseAllVo purchaseAllVo) {
        return purchaseService.addPurchaseToAudit(purchaseAllVo);
    }

    // ---------------------------------编辑库存------------------------------------------------
    // 添加 & 暂存[未提交] status=1
    @PostMapping("editPurchase")
    public Result<List<PurchaseItem>> editPurchase(@RequestBody PurchaseAllVo purchaseAllVo) {
        return purchaseService.editPurchase(purchaseAllVo);
    }

    // 添加 & 提交审核[待审核] status=2
    @PostMapping("editPurchaseToAudit")
    public Result<List<PurchaseItem>> editPurchaseToAudit(@RequestBody PurchaseAllVo purchaseAllVo) {
        return purchaseService.editPurchaseToAudit(purchaseAllVo);
    }

    // ------------------------------------------------------------------------------------------

    // 采购入库 & 提交审核[待审核] status=2
    @PostMapping("doAudit/{purchaseId}")
    public void doAudit(@PathVariable String purchaseId) {
        purchaseService.doAudit(purchaseId);
    }

    // 审核通过 status=3
    @PostMapping("auditPass/{purchaseId}")
    public void auditPass(@PathVariable String purchaseId) {
        purchaseService.auditPass(purchaseId);
    }

    //审核不通过 status=4
    @PostMapping("auditNoPass/{purchaseId}/{value}")
    public void auditNoPass(@PathVariable String purchaseId, @PathVariable String value) {
        purchaseService.auditNoPass(purchaseId, value);
    }

    // 作废 status=5
    @PostMapping("doInvalid/{purchaseId}")
    public void doInvalid(@PathVariable String purchaseId) {
        purchaseService.doInvalid(purchaseId);
    }

    // 分页查询所有的待审核采购单数据
    @PostMapping("listPurchasePendingForPage/{current}/{size}")
    public Result<IPage<Purchase>> listPurchasePendingForPage(
            @PathVariable Integer current,
            @PathVariable Integer size,
            @RequestBody PurchaseVo purchaseVo  //传递前端用于查询的参数
    ) {
        return purchaseService.listPurchasePendingForPage(current, size, purchaseVo);
    }

    // 执行入库
    @PostMapping("doInventory/{purchaseId}")
    public void doInventory(@PathVariable String purchaseId) {
        purchaseService.doInventory(purchaseId);
    }

}
