package com.aaa.doctor.feign;

import com.aaa.core.entity.CheckItem;
import com.aaa.doctor.vo.NewCareVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//调用其他模块里的方法
@FeignClient(value = "mmp-services-check")
public interface CheckFegin {
    // 查询检查的项目
    @PostMapping("check/api/checkItem/queryCheckItem/")
    Page<CheckItem> queryCheckItem(@RequestBody NewCareVo newCareVo);
}
