package com.aaa.charge.fegin;

import com.aaa.core.entity.DictData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "mmp-services-system")
public interface OrderCharFeign {
    //服务与服务之间的调用返回类型为实体类，前端调用返回result
    @GetMapping("/system/dict/getAll")
    List<DictData> getAll();

}
