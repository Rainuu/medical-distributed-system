package com.aaa.system.controller.api;

import com.aaa.core.entity.CheckItem;
import com.aaa.core.entity.RegisteredItem;
import com.aaa.core.vo.Result;
import com.aaa.system.service.RegisteredItemService;
import com.aaa.system.vo.RegisteredItemVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system/api/RegisteredTtem")
public class ApiRegisteredItemController {
    @Autowired
    private RegisteredItemService service;

    @PostMapping("getAll")
    public Result getAll(Page page, RegisteredItemVo registeredItemVo){
        Result all = service.getAll(page, registeredItemVo);
        return all;
    }

    @PostMapping("saveAndUp")
    public Result saveAndUp(RegisteredItem registeredItem){
        return service.saveAndUp(registeredItem);
    }

    @DeleteMapping("{id}")
    public Result delInfo(@PathVariable Long id){
        return service.delInfo(id);
    }
}
