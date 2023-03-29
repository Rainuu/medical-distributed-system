package com.aaa.system.controller.api;

import com.aaa.core.entity.DictType;
import com.aaa.core.vo.Result;
import com.aaa.system.service.DictTypeService;
import com.aaa.system.vo.DictTypeVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/api/dictType")
public class ApiDeptTypeController {
    @Autowired
    private DictTypeService service;

@PostMapping("getAll")
    public Result getAll(Page page, DictTypeVo dictTypeVo){
    return service.getAll(page,dictTypeVo);
}

@PostMapping("saveAndUp")
    public Result saveAndUp(DictType dictType){

    return service.saveAndUp(dictType);
}

@DeleteMapping("{id}")
    public Result delDict(@PathVariable Long id){
   return service.delDictType(id);
}

@GetMapping("getInfo")
    public Result getInfo(){
    return service.getInfo();
}

}
