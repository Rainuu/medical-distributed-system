package com.aaa.check.controller.api;

import com.aaa.check.service.CheckItemService;
import com.aaa.core.entity.CheckItem;
import com.aaa.core.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 检查费用表(SysCheckItem)表控制层
 *
 * @author makejava
 * @since 2023-03-24 17:32:14
 */
@RestController
@RequestMapping("check/api/checkItem")
public class CheckItemController {
    /**
     * 服务对象
     */
    @Autowired
    private CheckItemService checkItemService;

    @GetMapping("selectAllCheckItem")
    public Result<List<CheckItem>> selectAllCheckItem (){
        return checkItemService.selectAllCheckItem();
    }



}
