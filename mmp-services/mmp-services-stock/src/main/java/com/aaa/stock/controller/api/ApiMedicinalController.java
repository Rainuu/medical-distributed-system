package com.aaa.stock.controller.api;

import com.aaa.core.entity.Medicines;
import com.aaa.core.vo.Result;
import com.aaa.stock.service.MedicinalService;
import com.aaa.stock.vo.MedicinalVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("getProducerName")
    public Result<String> getProducerName(){
        //为了获取producerName
        return medicinalService.getProducerName();
    }

    // 修改或添加
    @PostMapping("saveAndUpdate")
    public Result saveAndUpdate(Medicines medicines){
        if (medicinalService.saveAndUpdate(medicines)){
            return new Result(200,"操作成功",true);
        }else {
            return new Result(500,"操作失败",false);
        }
    }

    // 删除
    @DeleteMapping("delById/{medicinesId}")
    public Result delById(@PathVariable Long medicinesId){
        if (medicinalService.delById(medicinesId)){
            return new Result(200,"删除成功",true);
        }else {
            return new Result(500,"删除失败",false);
        }
    }
}
