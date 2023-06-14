package com.aaa.stock.service.impl;

import com.aaa.core.entity.DictData;
import com.aaa.core.vo.Result;
import com.aaa.stock.feign.Feign;
import com.aaa.stock.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@Service
public class FeignServiceImpl implements FeignService {

    //注入feign
    @Autowired
    private Feign feign;

    @Override
    public Result<List<DictData>> getOpenFeign() {
        List<DictData> all = feign.getAll();
//        System.out.println(all.toString());
        return new Result(200, "成功", all);
    }
}
