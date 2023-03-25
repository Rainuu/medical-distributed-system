package com.aaa.stock.feign;

import com.aaa.core.entity.DictData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/25 15:53
 */
@FeignClient(value = "mmp-services-system") //获取system的nacos服务名，注册feign服务
public interface ProducterFeign {

    @GetMapping("/system/dict/getAll")
    List<DictData> getAll();
}
