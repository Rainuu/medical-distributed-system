package com.aaa.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @TableName stock_purchase_item
 */
@TableName(value = "stock_purchase_item")
@Data
public class PurchaseItem implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 详情ID
     */
    @TableId
    private String itemId;
    /**
     * 采购单据ID
     */
    private String purchaseId;
    /**
     * 药品ID
     */
    private String medicinesId;
    /**
     * 采购数量
     */
    private Integer purchaseNumber;
    /**
     * 批发价
     */
    private BigDecimal tradePrice;
    /**
     * 批发额
     */
    private BigDecimal tradeTotalAmount;
    /**
     * 药品生产批次号
     */
    private String batchNumber;
    /**
     * 备注
     */
    private String remark;
    /**
     * 药品名称
     */
    private String medicinesName;
    /**
     * 药品分类 sys_dict_data表his_medicines_type
     */
    private String medicinesType;
    /**
     * 处方类型 sys_dict_data表his_prescription_type
     */
    private String prescriptionType;
    /**
     * 生产厂家ID
     */
    private String producterId;
    /**
     * 换算量
     */
    private Integer conversion;
    /**
     * 单位（g/条）
     */
    private String unit;
    /**
     * 关键字
     */
    private String keywords;

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
        PurchaseItem other = (PurchaseItem) that;
        return (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
                && (this.getPurchaseId() == null ? other.getPurchaseId() == null : this.getPurchaseId().equals(other.getPurchaseId()))
                && (this.getMedicinesId() == null ? other.getMedicinesId() == null : this.getMedicinesId().equals(other.getMedicinesId()))
                && (this.getPurchaseNumber() == null ? other.getPurchaseNumber() == null : this.getPurchaseNumber().equals(other.getPurchaseNumber()))
                && (this.getTradePrice() == null ? other.getTradePrice() == null : this.getTradePrice().equals(other.getTradePrice()))
                && (this.getTradeTotalAmount() == null ? other.getTradeTotalAmount() == null : this.getTradeTotalAmount().equals(other.getTradeTotalAmount()))
                && (this.getBatchNumber() == null ? other.getBatchNumber() == null : this.getBatchNumber().equals(other.getBatchNumber()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getMedicinesName() == null ? other.getMedicinesName() == null : this.getMedicinesName().equals(other.getMedicinesName()))
                && (this.getMedicinesType() == null ? other.getMedicinesType() == null : this.getMedicinesType().equals(other.getMedicinesType()))
                && (this.getPrescriptionType() == null ? other.getPrescriptionType() == null : this.getPrescriptionType().equals(other.getPrescriptionType()))
                && (this.getProducterId() == null ? other.getProducterId() == null : this.getProducterId().equals(other.getProducterId()))
                && (this.getConversion() == null ? other.getConversion() == null : this.getConversion().equals(other.getConversion()))
                && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
                && (this.getKeywords() == null ? other.getKeywords() == null : this.getKeywords().equals(other.getKeywords()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getPurchaseId() == null) ? 0 : getPurchaseId().hashCode());
        result = prime * result + ((getMedicinesId() == null) ? 0 : getMedicinesId().hashCode());
        result = prime * result + ((getPurchaseNumber() == null) ? 0 : getPurchaseNumber().hashCode());
        result = prime * result + ((getTradePrice() == null) ? 0 : getTradePrice().hashCode());
        result = prime * result + ((getTradeTotalAmount() == null) ? 0 : getTradeTotalAmount().hashCode());
        result = prime * result + ((getBatchNumber() == null) ? 0 : getBatchNumber().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getMedicinesName() == null) ? 0 : getMedicinesName().hashCode());
        result = prime * result + ((getMedicinesType() == null) ? 0 : getMedicinesType().hashCode());
        result = prime * result + ((getPrescriptionType() == null) ? 0 : getPrescriptionType().hashCode());
        result = prime * result + ((getProducterId() == null) ? 0 : getProducterId().hashCode());
        result = prime * result + ((getConversion() == null) ? 0 : getConversion().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getKeywords() == null) ? 0 : getKeywords().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemId=").append(itemId);
        sb.append(", purchaseId=").append(purchaseId);
        sb.append(", medicinesId=").append(medicinesId);
        sb.append(", purchaseNumber=").append(purchaseNumber);
        sb.append(", tradePrice=").append(tradePrice);
        sb.append(", tradeTotalAmount=").append(tradeTotalAmount);
        sb.append(", batchNumber=").append(batchNumber);
        sb.append(", remark=").append(remark);
        sb.append(", medicinesName=").append(medicinesName);
        sb.append(", medicinesType=").append(medicinesType);
        sb.append(", prescriptionType=").append(prescriptionType);
        sb.append(", producterId=").append(producterId);
        sb.append(", conversion=").append(conversion);
        sb.append(", unit=").append(unit);
        sb.append(", keywords=").append(keywords);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
