package com.aaa.sso.feign;

import com.aaa.core.entity.LoginInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "mmp-services-system")
public interface LoginLogFeign {
    @PostMapping("/system/loginInfo/addLoginLog")
    int addLoginLog(@RequestBody LoginInfo loginInfo);
}
