package com.aaa.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 退费订单详情表
 *
 * @TableName his_order_backfee_item
 */
@TableName(value = "his_order_backfee_item")
@Data
public class OrderBackfeeItem implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 详情ID和his_care_order_*表里面的ID一样
     */
    @TableId
    private String itemId;
    /**
     * 处方ID【备用】
     */
    private String coId;
    /**
     * 项目名称
     */
    private String itemName;
    /**
     * 项目价格
     */
    private BigDecimal itemPrice;
    /**
     * 项目数量
     */
    private Integer itemNum;
    /**
     * 小计
     */
    private Integer itemAmount;
    /**
     * 订单ID his_oder_backfee主键
     */
    private String backId;
    /**
     * 项目类型0药品  还是1检查项
     */
    private String itemType;
    /**
     * 状态，0未支付，1已支付，2，已退费  3，已完成   字典表 his_order_details_status
     */
    private String status;

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
        OrderBackfeeItem other = (OrderBackfeeItem) that;
        return (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
                && (this.getCoId() == null ? other.getCoId() == null : this.getCoId().equals(other.getCoId()))
                && (this.getItemName() == null ? other.getItemName() == null : this.getItemName().equals(other.getItemName()))
                && (this.getItemPrice() == null ? other.getItemPrice() == null : this.getItemPrice().equals(other.getItemPrice()))
                && (this.getItemNum() == null ? other.getItemNum() == null : this.getItemNum().equals(other.getItemNum()))
                && (this.getItemAmount() == null ? other.getItemAmount() == null : this.getItemAmount().equals(other.getItemAmount()))
                && (this.getBackId() == null ? other.getBackId() == null : this.getBackId().equals(other.getBackId()))
                && (this.getItemType() == null ? other.getItemType() == null : this.getItemType().equals(other.getItemType()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getCoId() == null) ? 0 : getCoId().hashCode());
        result = prime * result + ((getItemName() == null) ? 0 : getItemName().hashCode());
        result = prime * result + ((getItemPrice() == null) ? 0 : getItemPrice().hashCode());
        result = prime * result + ((getItemNum() == null) ? 0 : getItemNum().hashCode());
        result = prime * result + ((getItemAmount() == null) ? 0 : getItemAmount().hashCode());
        result = prime * result + ((getBackId() == null) ? 0 : getBackId().hashCode());
        result = prime * result + ((getItemType() == null) ? 0 : getItemType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", itemName=").append(itemName);
        sb.append(", itemPrice=").append(itemPrice);
        sb.append(", itemNum=").append(itemNum);
        sb.append(", itemAmount=").append(itemAmount);
        sb.append(", backId=").append(backId);
        sb.append(", itemType=").append(itemType);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
