package com.aaa.check.controller.api;

import com.aaa.check.service.CareOrderItemService;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.CheckItemVo;
import com.aaa.core.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 开诊细表(HisCareOrderItem)表控制层
 *
 * @author makejava
 * @since 2023-03-27 10:56:56
 */
@RestController
@RequestMapping("check/api/CareOrderItem")
public class CareOrderItemController {
    /**
     * 服务对象
     */
    @Autowired
    private CareOrderItemService careOrderItemService;

    @PostMapping("selectAll")
    public Result<List<CareOrderItem>> selectAll (
            @RequestBody CheckItemVo checkItemVo
    ){
        return careOrderItemService.selectAll(checkItemVo);
    }



}
