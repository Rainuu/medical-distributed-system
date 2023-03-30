package com.aaa.system.controller.api;

import com.aaa.core.entity.Notice;
import com.aaa.core.vo.Result;
import com.aaa.system.service.NoticeService;
import com.aaa.system.vo.NoticeVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system/api/notice")
public class ApiNoticeController {
    @Autowired
    private NoticeService service;

    @PostMapping("getAll")
    public Result getAll(Page page, NoticeVo noticeVo){
        return service.getAll(page,noticeVo);
    };

    @PostMapping("saveAndUp")
    public Result saveAndUp(Notice notice){
        return service.saveAndUp(notice);
    }

    @DeleteMapping("{id}")
    public Result delById(@PathVariable Long id){
        return service.delById(id);
    }
}
