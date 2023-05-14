package com.aaa.system.controller.api;


import com.aaa.core.entity.DictData;
import com.aaa.core.vo.Result;
import com.aaa.system.service.DictDataService;
import com.aaa.system.vo.DictDataVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("system/api/dict/data")
public class ApiDictDataController {
    @Autowired
    public DictDataService service;

    @GetMapping("findByType/{type}")
    public Result<List<DictData>> list(@PathVariable String type) {
        return new Result<List<DictData>>(200, "", service.findByType(type));
    }

    @GetMapping("getall")
    public Result<List<DictData>> getAll() {
        return new Result<List<DictData>>(200, "", service.getall());
    }

    @PostMapping("getInfoById")
    public Result getInfoById(DictDataVo dictDataVo, Page page, Long dictId) {
        return service.getInfoById(dictDataVo, page, dictId);
    }

    @PostMapping("saveAndUp")
    public Result saveAndUp(DictData dictData) {
        return service.saveAndUp(dictData);
    }

    @DeleteMapping("{id}")
    public Result delInfo(@PathVariable Long id) {
        return service.delInfo(id);
    }

    @GetMapping("{id}")
    public Result selectById(@PathVariable Long id) {
        return service.selectById(id);
    }

    @GetMapping("tongbu")
    public Result tongbu() {
        return service.huancun();
    }
}
