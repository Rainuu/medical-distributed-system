package com.aaa.doctor.feign;

import com.aaa.core.entity.RegisteredItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/27 10:23
 */
@FeignClient(value = "mmp-services-system")
public interface PatienFeign {
    @GetMapping("system/registeredItem/getAll")
    List<RegisteredItem> getAll();


}

