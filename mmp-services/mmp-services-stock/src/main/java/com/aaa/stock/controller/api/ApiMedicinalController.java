package com.aaa.stock.controller.api;

import com.aaa.core.entity.DictData;
import com.aaa.core.entity.Medicines;
import com.aaa.core.entity.Producer;
import com.aaa.core.vo.Result;
import com.aaa.stock.feign.Feign;
import com.aaa.stock.service.MedicinalService;
import com.aaa.stock.vo.MedicinalVo;
import com.aaa.stock.vo.ProducterVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/27 16:51
 */
@RestController
@RequestMapping("stock/api/medicinal")
public class ApiMedicinalController {
    @Autowired
    private MedicinalService medicinalService;

    //查询&分页
    @PostMapping("getAll/{current}/{size}")
    public Result<IPage<Medicines>> getAll(
            @PathVariable Integer current,
            @PathVariable Integer size,
            @RequestBody MedicinalVo medicinalVo  //把JSON转Java对象
    ){
        return medicinalService.getMedById(current,size,medicinalVo);
    }

    //处理字典
    @GetMapping("status")
    public Result<List<DictData>> status(){
        return medicinalService.getOpenFeign();
    }

}
