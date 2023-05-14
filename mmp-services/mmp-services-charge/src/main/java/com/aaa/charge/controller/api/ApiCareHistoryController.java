package com.aaa.charge.controller.api;

import com.aaa.charge.service.HisCareChargeltemService;
import com.aaa.charge.service.HisCareHistoryService;
import com.aaa.charge.vo.PostObjVo;
import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private HisCareChargeltemService hisCareChargeltemService;


    //处方收费 根据regid 查询三张表数据
    @GetMapping("list/{regId}")
    public Result<Map<String, Object>> list(@PathVariable String regId) {
        return hisCareHistoryService.findcareHistory(regId);
    }

    //处方收费 现金支付修改状态
    @PostMapping("updateBystatus")
    public Result<List<CareOrderItem>> updateBystatus(@RequestBody PostObjVo postObjVo) {
        return hisCareChargeltemService.updateBystatus(postObjVo);
    }


}