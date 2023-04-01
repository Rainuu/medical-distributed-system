package com.aaa.check.controller.api;

import com.aaa.check.service.CheckResultService;
import com.aaa.core.vo.CheckResultVo;
import com.aaa.core.entity.CheckResult;
import com.aaa.core.vo.CheckItemVo;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * (HisCheckResult)表控制层
 *
 * @author makejava
 * @since 2023-03-24 09:37:20
 */
@RestController
@RequestMapping("check/api/checkResult")
public class CheckResultController {
    /**
     * 服务对象
     */
    @Autowired
    private CheckResultService checkResultService;

    @PostMapping("listStatus/{current}/{size}")
    public Result<IPage<CheckResult>> listStatus(
            @PathVariable Integer current,
            @PathVariable Integer size,
            @RequestBody CheckItemVo checkItemVo
    ){
        return checkResultService.getByPageStatus(current,size,checkItemVo);
    }

    @GetMapping("addMsg")
    public Result addMsg(String cocId,String textarea,String name,String url){
        return checkResultService.addMsg(cocId,textarea,name,url);
    }

    @GetMapping("addAll")
    public Result addAll(CheckResult checkResult){
        return checkResultService.addAll(checkResult);
    }
}
