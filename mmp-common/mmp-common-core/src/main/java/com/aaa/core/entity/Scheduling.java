package com.aaa.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 排班信息表
 * @TableName his_scheduling
 */
@TableName(value ="his_scheduling")
@Data
public class Scheduling implements Serializable {
    /**
     * 医生ID
     */
    private Integer userId;

    /**
     * 科室ID
     */
    private Integer deptId;

    /**
     * 值班日期
     */
    private String schedulingDay;

    /**
     * 排班时段1上午  2下午 3晚上 字典表数据翻译
     */
    private String subsectionType;

    /**
     * 排班类型1 门诊 2 急诊 字典表数据翻译
     */
    private String schedulingType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者
     */
    private String createBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Scheduling other = (Scheduling) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
            && (this.getSchedulingDay() == null ? other.getSchedulingDay() == null : this.getSchedulingDay().equals(other.getSchedulingDay()))
            && (this.getSubsectionType() == null ? other.getSubsectionType() == null : this.getSubsectionType().equals(other.getSubsectionType()))
            && (this.getSchedulingType() == null ? other.getSchedulingType() == null : this.getSchedulingType().equals(other.getSchedulingType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getSchedulingDay() == null) ? 0 : getSchedulingDay().hashCode());
        result = prime * result + ((getSubsectionType() == null) ? 0 : getSubsectionType().hashCode());
        result = prime * result + ((getSchedulingType() == null) ? 0 : getSchedulingType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", deptId=").append(deptId);
        sb.append(", schedulingDay=").append(schedulingDay);
        sb.append(", subsectionType=").append(subsectionType);
        sb.append(", schedulingType=").append(schedulingType);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
