package com.aaa.charge.fegin;

import com.aaa.core.entity.DictData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(value = "mmp-services-stock")
public interface CharFeign {
    @PostMapping("stock/api/medicinal/num")
    public Boolean num(@RequestParam String num,@RequestParam String itemName);

}
