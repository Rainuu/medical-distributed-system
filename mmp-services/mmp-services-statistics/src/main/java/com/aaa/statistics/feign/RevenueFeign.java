package com.aaa.statistics.feign;

import com.aaa.core.entity.CareOrderItem;
import com.aaa.core.entity.CheckResult;
import com.aaa.core.entity.OrderBackfee;
import com.aaa.core.entity.OrderCharge;
import com.aaa.core.vo.CheckResultVo;
import com.aaa.core.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "mmp-services-charge") //获取system的nacos服务名，注册feign服务
public interface RevenueFeign {

    @PostMapping("charge/hisOrderCharge/list")
    public List<OrderCharge> InComelist(String[] create_time);
    @PostMapping("hisOrderBackfee/list")
    public List<OrderBackfee> BackFeelist(String[] create_time);
    @PostMapping("charge/hisCareOrderItem/list")
    public List<CareOrderItem> alist(@RequestParam String itemName,@RequestParam String dateRange1,@RequestParam String dateRange2);
    @PostMapping("charge/hisCareOrderItem/getData")
    public List<Map<String,Object>> getData(@RequestParam String itemName,@RequestParam String dateRange1,@RequestParam String dateRange2);
}
