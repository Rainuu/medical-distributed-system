package com.aaa.doctor.feign;

import com.aaa.core.entity.Medicines;
import com.aaa.core.vo.Result;
import com.aaa.doctor.vo.NewCareVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//调用其他模块里的方法
@FeignClient(value = "mmp-services-stock")
public interface StockFegin {
    // 远程调用 查询 药品信息表全部
    @PostMapping("/stock/api/medicinal/MeDicinesController")
    Result<Page<Medicines>> MeDicinesController(@RequestBody NewCareVo newCareVo);
}
