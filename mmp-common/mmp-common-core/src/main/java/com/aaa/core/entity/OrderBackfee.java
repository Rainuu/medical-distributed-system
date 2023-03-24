package com.aaa.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 退费主表
 * @TableName his_order_backfee
 */
@TableName(value ="his_order_backfee")
@Data
public class OrderBackfee implements Serializable {
    /**
     * 退费ID
     */
    @TableId
    private String backId;

    /**
     * 总费用
     */
    private Long backAmount;

    /**
     * 病历ID
     */
    private String chId;

    /**
     * 挂号单
     */
    private String regId;

    /**
     * 患者名称
     */
    private String patientName;

    /**
     * 订单状态0未退费  1 退费成功 2退费失败  字典表his_backfee_status
     */
    private String backStatus;

    /**
     * 退费类型0现金1支付宝  字典表his_pay_type_status
     */
    private String backType;

    /**
     * 关联订单ID his_order_charge
     */
    private String orderId;

    /**
     * 退费交易ID
     */
    private String backPlatformId;

    /**
     * 退费交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date backTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

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
        OrderBackfee other = (OrderBackfee) that;
        return (this.getBackId() == null ? other.getBackId() == null : this.getBackId().equals(other.getBackId()))
            && (this.getBackAmount() == null ? other.getBackAmount() == null : this.getBackAmount().equals(other.getBackAmount()))
            && (this.getChId() == null ? other.getChId() == null : this.getChId().equals(other.getChId()))
            && (this.getRegId() == null ? other.getRegId() == null : this.getRegId().equals(other.getRegId()))
            && (this.getPatientName() == null ? other.getPatientName() == null : this.getPatientName().equals(other.getPatientName()))
            && (this.getBackStatus() == null ? other.getBackStatus() == null : this.getBackStatus().equals(other.getBackStatus()))
            && (this.getBackType() == null ? other.getBackType() == null : this.getBackType().equals(other.getBackType()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getBackPlatformId() == null ? other.getBackPlatformId() == null : this.getBackPlatformId().equals(other.getBackPlatformId()))
            && (this.getBackTime() == null ? other.getBackTime() == null : this.getBackTime().equals(other.getBackTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBackId() == null) ? 0 : getBackId().hashCode());
        result = prime * result + ((getBackAmount() == null) ? 0 : getBackAmount().hashCode());
        result = prime * result + ((getChId() == null) ? 0 : getChId().hashCode());
        result = prime * result + ((getRegId() == null) ? 0 : getRegId().hashCode());
        result = prime * result + ((getPatientName() == null) ? 0 : getPatientName().hashCode());
        result = prime * result + ((getBackStatus() == null) ? 0 : getBackStatus().hashCode());
        result = prime * result + ((getBackType() == null) ? 0 : getBackType().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getBackPlatformId() == null) ? 0 : getBackPlatformId().hashCode());
        result = prime * result + ((getBackTime() == null) ? 0 : getBackTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", backId=").append(backId);
        sb.append(", backAmount=").append(backAmount);
        sb.append(", chId=").append(chId);
        sb.append(", regId=").append(regId);
        sb.append(", patientName=").append(patientName);
        sb.append(", backStatus=").append(backStatus);
        sb.append(", backType=").append(backType);
        sb.append(", orderId=").append(orderId);
        sb.append(", backPlatformId=").append(backPlatformId);
        sb.append(", backTime=").append(backTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
