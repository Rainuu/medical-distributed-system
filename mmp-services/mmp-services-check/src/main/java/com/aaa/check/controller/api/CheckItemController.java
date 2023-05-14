package com.aaa.check.controller.api;

import com.aaa.check.service.CheckItemService;
import com.aaa.check.vo.NewCareVo;
import com.aaa.core.entity.CheckItem;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //查询所有检查项目
    @GetMapping("selectAllCheckItem")
    public Result<List<CheckItem>> selectAllCheckItem() {
        return checkItemService.selectAllCheckItem();
    }


    //提供doctor查询检查项目接口
    @PostMapping("queryCheckItem/")
    public Page<CheckItem> queryCheckItem(@RequestBody NewCareVo newCareVo) {
        return checkItemService.selectByKey(newCareVo);
    }


}
