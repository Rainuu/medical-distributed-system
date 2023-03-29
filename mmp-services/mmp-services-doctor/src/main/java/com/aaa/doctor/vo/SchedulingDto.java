package com.aaa.doctor.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Map;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/29 10:47
 * 该实体类就是前端需要的数据格式对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingDto {
    private Long userId;
    private Long deptId;
    private String subsectionType;//上午1  下午2  晚上3
    private Collection<String> schedulingType;//存放周一到周日的subsectionType时间段 是门诊还是急诊的数据

    //存放星期值班的记录 key 指日期    周一到周日的日期字符串   value 有值就1  2   没有值就""
    @JsonIgnore
    private Map<String,String> record;


    public SchedulingDto(Long userId, Long deptId, String subsectionType, Map<String, String> record) {
        this.userId = userId;
        this.deptId = deptId;
        this.subsectionType = subsectionType;
        this.record = record;
    }
}
