package com.aaa.stock.controller.api;

import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;
import com.aaa.stock.service.ProducterService;
import com.aaa.stock.vo.ProducterVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform

 */
@RestController
@RequestMapping("stock/api/producter")
public class ApiProducterController {
    // 注入service层
    @Autowired
    private ProducterService producterService;

    // 查询 & 分页 & 模糊查询————给前端提供的接口，使用Vo作为容器把前端需要的参数装入并传递给前端
    @PostMapping("getAll/{current}/{size}")
    public Result<IPage<Producer>> getAll(
            @PathVariable Integer current,
            @PathVariable Integer size,
            @RequestBody ProducterVo producterVo
    ) {
        return producterService.getAll(current, size, producterVo);
    }

    // 删除
    @DeleteMapping("delById/{id}")
    public Result delById(@PathVariable Long id) {
        if (producterService.delById(id)) {
            return new Result(200, "删除成功", true);
        } else {
            return new Result(500, "删除失败", false);
        }
    }

    // 修改 & 新增————Result返回值类型是前端需要的类型，传递的容器包含了需要的参数
    @PostMapping("saveAndUpdate")
    public Result saveAndUpdate(Producer producer) {
        if (producterService.saveAndUpdate(producer)) {
            return new Result(200, "操作成功", true);
        } else {
            return new Result(500, "操作失败", false);
        }
    }

    @PostMapping("delListById")
    public Result delListById(@RequestBody List<Producer> producerList) {
        producerList.forEach((Producer producer) -> {
            producterService.removeById(producer.getProducerId());
        });
        return new Result(200, "批量删除成功", true);
    }

    // 全查询接口
    @GetMapping("getAllDict")
    public Result<List<Producer>> getAllDict() {
        List<Producer> list = producterService.list();
        return new Result(200, "成功", list);
    }
}
