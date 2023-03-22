package com.aaa.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName his_registration
 */
@TableName(value ="his_registration")
@Data
public class Registration implements Serializable {
    /**
     * 挂号流水
     */
    @TableId
    private String registrationId;

    /**
     * 患者ID
     */
    private String patientId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 接诊医生ID
     */
    private Long userId;

    /**
     * 接诊医生姓名
     */
    private String doctorName;

    /**
     * 科室ID
     */
    private Long deptId;

    /**
     * 挂号费用ID
     */
    private Long registrationItemId;

    /**
     * 挂号总金额
     */
    private BigDecimal registrationAmount;

    /**
     * 挂号编号
     */
    private Integer registrationNumber;

    /**
     * 挂号状态0未收费,1待就诊，2,就诊中，3，就诊完成，4，已退号，5 作废
     */
    private String registrationStatus;

    /**
     * 就诊日期
     */
    private String visitDate;

    /**
     * 排班类型1 门诊 2 急诊 字典表数据翻译
     */
    private String schedulingType;

    /**
     * 排班时段1上午  2下午 3晚上 字典表数据翻译
     */
    private String subsectionType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
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
        Registration other = (Registration) that;
        return (this.getRegistrationId() == null ? other.getRegistrationId() == null : this.getRegistrationId().equals(other.getRegistrationId()))
            && (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getPatientName() == null ? other.getPatientName() == null : this.getPatientName().equals(other.getPatientName()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getDoctorName() == null ? other.getDoctorName() == null : this.getDoctorName().equals(other.getDoctorName()))
            && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
            && (this.getRegistrationItemId() == null ? other.getRegistrationItemId() == null : this.getRegistrationItemId().equals(other.getRegistrationItemId()))
            && (this.getRegistrationAmount() == null ? other.getRegistrationAmount() == null : this.getRegistrationAmount().equals(other.getRegistrationAmount()))
            && (this.getRegistrationNumber() == null ? other.getRegistrationNumber() == null : this.getRegistrationNumber().equals(other.getRegistrationNumber()))
            && (this.getRegistrationStatus() == null ? other.getRegistrationStatus() == null : this.getRegistrationStatus().equals(other.getRegistrationStatus()))
            && (this.getVisitDate() == null ? other.getVisitDate() == null : this.getVisitDate().equals(other.getVisitDate()))
            && (this.getSchedulingType() == null ? other.getSchedulingType() == null : this.getSchedulingType().equals(other.getSchedulingType()))
            && (this.getSubsectionType() == null ? other.getSubsectionType() == null : this.getSubsectionType().equals(other.getSubsectionType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRegistrationId() == null) ? 0 : getRegistrationId().hashCode());
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getPatientName() == null) ? 0 : getPatientName().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDoctorName() == null) ? 0 : getDoctorName().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getRegistrationItemId() == null) ? 0 : getRegistrationItemId().hashCode());
        result = prime * result + ((getRegistrationAmount() == null) ? 0 : getRegistrationAmount().hashCode());
        result = prime * result + ((getRegistrationNumber() == null) ? 0 : getRegistrationNumber().hashCode());
        result = prime * result + ((getRegistrationStatus() == null) ? 0 : getRegistrationStatus().hashCode());
        result = prime * result + ((getVisitDate() == null) ? 0 : getVisitDate().hashCode());
        result = prime * result + ((getSchedulingType() == null) ? 0 : getSchedulingType().hashCode());
        result = prime * result + ((getSubsectionType() == null) ? 0 : getSubsectionType().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", registrationId=").append(registrationId);
        sb.append(", patientId=").append(patientId);
        sb.append(", patientName=").append(patientName);
        sb.append(", userId=").append(userId);
        sb.append(", doctorName=").append(doctorName);
        sb.append(", deptId=").append(deptId);
        sb.append(", registrationItemId=").append(registrationItemId);
        sb.append(", registrationAmount=").append(registrationAmount);
        sb.append(", registrationNumber=").append(registrationNumber);
        sb.append(", registrationStatus=").append(registrationStatus);
        sb.append(", visitDate=").append(visitDate);
        sb.append(", schedulingType=").append(schedulingType);
        sb.append(", subsectionType=").append(subsectionType);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
