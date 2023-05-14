package com.aaa.stock.controller.api;

import com.aaa.core.entity.InventoryLog;
import com.aaa.core.vo.Result;
import com.aaa.stock.service.InventoryLogService;
import com.aaa.stock.vo.InventoryLogVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/4/3 9:22
 */
@RestController
@RequestMapping("stock/api/InventoryLog") //给前端提供的路径
public class InventoryLogController {

    @Autowired
    private InventoryLogService inventoryLogService;

    //查询 & 分页 模糊
    @PostMapping("getAll/{current2}/{size2}")
    public Result<IPage<InventoryLog>> getAll(
            @PathVariable Integer current2,
            @PathVariable Integer size2,
            @RequestBody InventoryLogVo inventoryLogVo  //把JSON转Java对象
    ) {
        return inventoryLogService.getAll(current2, size2, inventoryLogVo);
    }

}
