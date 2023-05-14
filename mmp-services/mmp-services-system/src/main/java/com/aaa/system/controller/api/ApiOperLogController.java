package com.aaa.system.controller.api;

import com.aaa.core.vo.Result;
import com.aaa.system.service.OpenLogService;
import com.aaa.system.vo.OpenLogVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system/api/operLog")
public class ApiOperLogController {
    @Autowired
    private OpenLogService service;

    @PostMapping("getAll")
    public Result getAll(Page page, OpenLogVo openLogVo) {
        return service.getAll(page, openLogVo);
    }

    @DeleteMapping("{id}")
    public Result delById(@PathVariable Long id) {
        return service.delOperLog(id);
    }

    @GetMapping("clean")
    public Result clean() {
        return service.clean();
    }
}
