package com.aaa.charge.controller.api;

import com.aaa.charge.service.HisCareHistoryService;
import com.aaa.core.entity.CareHistory;
import com.aaa.core.entity.OrderBackfeeItem;
import com.aaa.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 病例表(HisCareHistory)表控制层
 *
 * @author makejava
 * @since 2023-03-27 09:37:34
 */
@RestController
@RequestMapping("charge/api/hisCareHistory")
public class ApiCareHistoryController {
    /**
     * 服务对象
     */
    @Autowired
    private HisCareHistoryService hisCareHistoryService;


    @GetMapping("list/{regId}")
    public Result<Map<String,Object>> list(@PathVariable String regId){
        return  hisCareHistoryService.findcareHistory(regId);
    }


}