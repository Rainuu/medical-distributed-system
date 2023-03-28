package com.aaa.stock.controller.api;

import com.aaa.core.entity.DictData;
import com.aaa.core.entity.Producer;
import com.aaa.core.entity.User;
import com.aaa.core.vo.Result;
import com.aaa.stock.service.ProducterService;
import com.aaa.stock.vo.ProducterVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/24 11:06
 */
@RestController
@RequestMapping("stock/api/producter") //给前端提供的路径
public class ApiProducterController {

    @Autowired
    private ProducterService producterService;

    //查询&分页
    @PostMapping("getAll/{current}/{size}")
    public Result<IPage<Producer>> getAll(
            @PathVariable Integer current,
            @PathVariable Integer size,
            @RequestBody ProducterVo producterVo  //把JSON转Java对象
            ){
        return producterService.getAll(current,size,producterVo);
    }

    // 删除
    @DeleteMapping("delById/{id}")
    public Result delById(@PathVariable Long id){
        if (producterService.delById(id)){
            return new Result(200,"删除成功",true);
        }else {
            return new Result(500,"删除失败",false);
        }
    }

    // 修改或添加
    @PostMapping("saveAndUpdate")
    public Result saveAndUpdate(Producer producer){  //Result返回值是数据库后端，Producer producer参数是前端用的
        if (producterService.saveAndUpdate(producer)){
            return new Result(200,"操作成功",true);
        }else {
            return new Result(500,"操作失败",false);
        }
    }

}
