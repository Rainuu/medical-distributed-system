package com.aaa.doctor.feign;

import com.aaa.core.entity.Medicines;
import com.aaa.core.vo.Result;
import com.aaa.stock.vo.MedicinalVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/4/1 11:03
 */
@FeignClient(value = "mmp-services-stock")
public interface NeweCareFeign {
    //查询&分页
    @PostMapping("stock/api/medicinal/getAll/{current}/{size}")
    public Result<IPage<Medicines>> getAll(
            @PathVariable Integer current,
            @PathVariable Integer size,
            @RequestBody MedicinalVo medicinalVo  //把JSON转Java对象
    );

}
