package com.aaa.sso.feign;

import com.aaa.core.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mmp-services-system")
public interface UserFeign {
    @GetMapping("/system/user/getByUsername/{username}")
public User getByUsername(@PathVariable String username);
}
