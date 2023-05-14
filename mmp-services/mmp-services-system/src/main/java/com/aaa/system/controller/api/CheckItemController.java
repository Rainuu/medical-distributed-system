package com.aaa.system.controller.api;

import com.aaa.core.entity.CheckItem;
import com.aaa.core.vo.Result;
import com.aaa.system.service.Impl.CheckItemServiceImpl;
import com.aaa.system.vo.CheckItemVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system/api/checkItem")
public class CheckItemController {
    @Autowired
    private CheckItemServiceImpl service;

    @PostMapping("getAll")
    public Result getAll(Page page, CheckItemVo checkItemVo) {
        Result all = service.getAll(page, checkItemVo);
        return all;
    }

    @PostMapping("saveAndUp")
    public Result saveAndUp(CheckItem checkItem) {
        return service.saveAndUp(checkItem);
    }

    @DeleteMapping("{id}")
    public Result delInfo(@PathVariable Long id) {
        return service.delInfo(id);
    }

}
