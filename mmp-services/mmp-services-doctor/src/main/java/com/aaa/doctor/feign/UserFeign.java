package com.aaa.doctor.feign;

import com.aaa.core.entity.Dept;
import com.aaa.core.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/29 10:34
 */
@FeignClient(value = "mmp-services-system")
public interface UserFeign {
    @PostMapping("/system/user/queryUsersNeedScheduling")
    List<User> queryUsersNeedScheduling(@RequestBody Map<String, Object> map);


    @GetMapping("/system/user/getByUsername/{username}")
    User getByUsername(@PathVariable String username);

    @GetMapping("/system/user/{id}")
    User queryById(@PathVariable Long id);

    @GetMapping("/system/user/getDeptByDeptId/{deptId}")
    Dept getDeptByDeptId(@PathVariable Long deptId);


    @GetMapping("system/dept/updDeptRegNumByDeptId/{deptId}/{regNum}")
    Boolean updDeptRegNumByDeptId(@PathVariable Long deptId, @PathVariable Integer regNum);


}
