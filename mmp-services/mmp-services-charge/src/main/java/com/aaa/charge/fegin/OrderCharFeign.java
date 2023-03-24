package com.aaa.charge.fegin;

import com.aaa.core.entity.DictData;
import com.aaa.system.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "mmp-services-charge")
public interface OrderCharFeign {
    //服务与服务之间的调用返回类型为实体类，前端调用返回result


    @GetMapping("/system/dict/getAll")
     List<DictData> getAll();

}
