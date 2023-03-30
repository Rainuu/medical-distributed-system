package com.aaa.doctor.feign;

import com.aaa.core.entity.RegisteredItem;
import com.aaa.core.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/27 10:23
 */
@FeignClient(value = "mmp-services-system")
public interface PatienFeign {
    @GetMapping("system/registeredItem/getAll")
    public List<RegisteredItem> getAll();


}

