package com.aaa.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 药用处方表
 * @TableName his_care_order
 */
@TableName(value ="his_care_order")
@Data
public class CareOrder implements Serializable {
    /**
     * 处方ID
     */
    @TableId
    private String coId;

    /**
     * 处方类型0药用处方1检查处方
     */
    private String coType;

    /**
     * 医生id
     */
    private Long userId;

    /**
     * 患者id
     */
    private String patientId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 关联病历id
     */
    private String chId;

    /**
     * 处方全额
     */
    private BigDecimal allAmount;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

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
        CareOrder other = (CareOrder) that;
        return (this.getCoId() == null ? other.getCoId() == null : this.getCoId().equals(other.getCoId()))
            && (this.getCoType() == null ? other.getCoType() == null : this.getCoType().equals(other.getCoType()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getPatientName() == null ? other.getPatientName() == null : this.getPatientName().equals(other.getPatientName()))
            && (this.getChId() == null ? other.getChId() == null : this.getChId().equals(other.getChId()))
            && (this.getAllAmount() == null ? other.getAllAmount() == null : this.getAllAmount().equals(other.getAllAmount()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCoId() == null) ? 0 : getCoId().hashCode());
        result = prime * result + ((getCoType() == null) ? 0 : getCoType().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getPatientName() == null) ? 0 : getPatientName().hashCode());
        result = prime * result + ((getChId() == null) ? 0 : getChId().hashCode());
        result = prime * result + ((getAllAmount() == null) ? 0 : getAllAmount().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", coId=").append(coId);
        sb.append(", coType=").append(coType);
        sb.append(", userId=").append(userId);
        sb.append(", patientId=").append(patientId);
        sb.append(", patientName=").append(patientName);
        sb.append(", chId=").append(chId);
        sb.append(", allAmount=").append(allAmount);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
