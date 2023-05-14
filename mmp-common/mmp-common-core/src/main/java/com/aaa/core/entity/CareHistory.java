package com.aaa.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 病例表
 *
 * @TableName his_care_history
 */
@TableName(value = "his_care_history")
@Data
public class CareHistory implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 病历ID
     */
    @TableId
    private String chId;
    /**
     * 医生id
     */
    private Long userId;
    /**
     * 医生姓名
     */
    private String userName;
    /**
     * 患者id
     */
    private String patientId;
    /**
     * 患者姓名
     */
    private String patientName;
    /**
     * 科室id
     */
    private Long deptId;
    /**
     * 科室名称
     */
    private String deptName;
    /**
     *
     */
    private String receiveType;
    /**
     * 是否传染，0否，1是 字典表属性his_contagious_status
     */
    private String isContagious;
    /**
     * 就诊时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date careTime;
    /**
     * 发病日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String caseDate;
    /**
     * 挂号单号
     */
    private String regId;
    /**
     * 主诉
     */
    private String caseTitle;
    /**
     * 诊断信息
     */
    private String caseResult;
    /**
     * 医生建议
     */
    private String doctorTips;
    /**
     * 备注
     */
    private String remark;
    /*
     * 患者的处方信息
     * */
    @TableField(exist = false)
    private List<CareOrder> CareOrderList;

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
        CareHistory other = (CareHistory) that;
        return (this.getChId() == null ? other.getChId() == null : this.getChId().equals(other.getChId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
                && (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
                && (this.getPatientName() == null ? other.getPatientName() == null : this.getPatientName().equals(other.getPatientName()))
                && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
                && (this.getDeptName() == null ? other.getDeptName() == null : this.getDeptName().equals(other.getDeptName()))
                && (this.getReceiveType() == null ? other.getReceiveType() == null : this.getReceiveType().equals(other.getReceiveType()))
                && (this.getIsContagious() == null ? other.getIsContagious() == null : this.getIsContagious().equals(other.getIsContagious()))
                && (this.getCareTime() == null ? other.getCareTime() == null : this.getCareTime().equals(other.getCareTime()))
                && (this.getCaseDate() == null ? other.getCaseDate() == null : this.getCaseDate().equals(other.getCaseDate()))
                && (this.getRegId() == null ? other.getRegId() == null : this.getRegId().equals(other.getRegId()))
                && (this.getCaseTitle() == null ? other.getCaseTitle() == null : this.getCaseTitle().equals(other.getCaseTitle()))
                && (this.getCaseResult() == null ? other.getCaseResult() == null : this.getCaseResult().equals(other.getCaseResult()))
                && (this.getDoctorTips() == null ? other.getDoctorTips() == null : this.getDoctorTips().equals(other.getDoctorTips()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getChId() == null) ? 0 : getChId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getPatientName() == null) ? 0 : getPatientName().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getDeptName() == null) ? 0 : getDeptName().hashCode());
        result = prime * result + ((getReceiveType() == null) ? 0 : getReceiveType().hashCode());
        result = prime * result + ((getIsContagious() == null) ? 0 : getIsContagious().hashCode());
        result = prime * result + ((getCareTime() == null) ? 0 : getCareTime().hashCode());
        result = prime * result + ((getCaseDate() == null) ? 0 : getCaseDate().hashCode());
        result = prime * result + ((getRegId() == null) ? 0 : getRegId().hashCode());
        result = prime * result + ((getCaseTitle() == null) ? 0 : getCaseTitle().hashCode());
        result = prime * result + ((getCaseResult() == null) ? 0 : getCaseResult().hashCode());
        result = prime * result + ((getDoctorTips() == null) ? 0 : getDoctorTips().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", chId=").append(chId);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", patientId=").append(patientId);
        sb.append(", patientName=").append(patientName);
        sb.append(", deptId=").append(deptId);
        sb.append(", deptName=").append(deptName);
        sb.append(", receiveType=").append(receiveType);
        sb.append(", isContagious=").append(isContagious);
        sb.append(", careTime=").append(careTime);
        sb.append(", caseDate=").append(caseDate);
        sb.append(", regId=").append(regId);
        sb.append(", caseTitle=").append(caseTitle);
        sb.append(", caseResult=").append(caseResult);
        sb.append(", doctorTips=").append(doctorTips);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}
