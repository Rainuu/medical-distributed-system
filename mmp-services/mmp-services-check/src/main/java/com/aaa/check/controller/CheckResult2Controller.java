package com.aaa.check.controller;

import com.aaa.check.service.CheckResultService;
import com.aaa.core.entity.CheckResult;
import com.aaa.core.vo.CheckResultVo;
import com.aaa.core.vo.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (HisCheckResult)表控制层
 *
 * @author makejava
 * @since 2023-03-24 09:37:20
 */
@RestController
@RequestMapping("check/checkResult")
public class CheckResult2Controller {
    /**
     * 服务对象
     */
    @Autowired
    private CheckResultService checkResultService;

    @PostMapping("selectAll")
    public Result<List<CheckResult>> selectAll(String checkItemId,String patientName, String dateRange1,String dateRange2){

        System.out.println("checkResultVo = " + checkItemId);
        return checkResultService.getByPage(checkItemId,patientName,dateRange1,dateRange2);
    }

    @PostMapping("selectAllTwo")
    public Result<List<CheckResult>> selectAllTwo(String checkItemId, String dateRange1,String dateRange2){
        return checkResultService.getByPageTwo(checkItemId,dateRange1,dateRange2);
    }


}
