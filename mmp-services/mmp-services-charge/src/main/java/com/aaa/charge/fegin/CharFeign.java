package com.aaa.charge.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mmp-services-stock")
public interface CharFeign {
    @PostMapping("stock/api/medicinal/num")
    Boolean num(@RequestParam String num, @RequestParam String itemName);

}
