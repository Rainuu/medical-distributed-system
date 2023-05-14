package com.aaa.sso.feign;


import com.aaa.core.entity.Menu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "mmp-services-system")
public interface MenuFeign {
    @GetMapping("/system/menu/findByUserid/{userId}")
    List<Menu> findByUserid(@PathVariable Long userId);
}
