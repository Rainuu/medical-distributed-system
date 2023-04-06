package com.aaa.check.feign;

import com.aaa.core.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * IntelliJ IDEA
 * CheckResultFeign
 * 好好学习天天练
 *
 * @author caojianbin
 * 2023/4/4
 */

@FeignClient(value = "mmp-services-system")
public interface CheckResultFeign {
    @GetMapping( "/system/user/getByUsername/{username}")
    public User getByUsername(@PathVariable String username);


}
