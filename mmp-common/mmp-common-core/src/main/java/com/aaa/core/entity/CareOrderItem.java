package com.aaa.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 开诊细表
 * @TableName his_care_order_item
 */
@TableName(value ="his_care_order_item")
@Data
public class CareOrderItem implements Serializable {
    /**
     * 开诊明细ID
     */
    @TableId
    private String itemId;

    /**
     * 所属处方id
     */
    private String coId;

    /**
     * 药品或者检查项目id
     */
    private String itemRefId;

    /**
     * 药品或检查项目名称
     */
    private String itemName;

    /**
     * 项目类型0药品  还是1检查项
     */
    private String itemType;

    /**
     * 数量
     */
    private BigDecimal num;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态，0未支付，1已支付，2，已退费  3，已完成 字典表his_order_details_status
     */
    private String status;

    @TableField(exist = false)
    private String coType;

    @TableField(exist = false)
    private String registrationId;

    @TableField(exist = false)
    private String patientName;

    @TableField(exist = false)
    private String patientId;







    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

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
        CareOrderItem other = (CareOrderItem) that;
        return (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
            && (this.getCoId() == null ? other.getCoId() == null : this.getCoId().equals(other.getCoId()))
            && (this.getItemRefId() == null ? other.getItemRefId() == null : this.getItemRefId().equals(other.getItemRefId()))
            && (this.getItemName() == null ? other.getItemName() == null : this.getItemName().equals(other.getItemName()))
            && (this.getItemType() == null ? other.getItemType() == null : this.getItemType().equals(other.getItemType()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getCoId() == null) ? 0 : getCoId().hashCode());
        result = prime * result + ((getItemRefId() == null) ? 0 : getItemRefId().hashCode());
        result = prime * result + ((getItemName() == null) ? 0 : getItemName().hashCode());
        result = prime * result + ((getItemType() == null) ? 0 : getItemType().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemId=").append(itemId);
        sb.append(", coId=").append(coId);
        sb.append(", itemRefId=").append(itemRefId);
        sb.append(", itemName=").append(itemName);
        sb.append(", itemType=").append(itemType);
        sb.append(", num=").append(num);
        sb.append(", price=").append(price);
        sb.append(", amount=").append(amount);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
