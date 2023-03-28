package com.aaa.stock.controller.api;

import com.aaa.core.entity.DictData;
import com.aaa.core.entity.Provider;
import com.aaa.core.vo.Result;
import com.aaa.stock.service.ProviderService;
import com.aaa.stock.vo.ProviderVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/26 16:09
 */
@RestController
@RequestMapping("stock/api/provider") //给前端提供的路径
public class ApiProviderController {

    // 装配注入service
    @Autowired
    private ProviderService providerService;

    // 查询&分页
    @PostMapping("getAll/{current}/{size}")
    public Result<IPage<Provider>> getAll(
            @PathVariable Integer current,
            @PathVariable Integer size,
            @RequestBody ProviderVo providerVo  //传递前端用于查询的参数
    ){
        return providerService.getAll(current,size,providerVo);
    }

    // 单个删除
    @DeleteMapping("delById/{id}")
    public Result delById(@PathVariable Long id){
        if (providerService.delById(id)){
            return new Result(200,"删除成功",true);
        }else {
            return new Result(500,"删除失败",false);
        }
    }

    // 修改或添加
    @PostMapping("saveAndUpdate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public Result saveAndUpdate(Provider provider){
        if (providerService.saveAndUpdate(provider)){
            return new Result(200,"操作成功",true);
        }else {
            return new Result(500,"操作失败",false);
        }
    }

}
