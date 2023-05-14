package com.aaa.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName stock_inventory_log
 */
@TableName(value = "stock_inventory_log")
@Data
public class InventoryLog implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 入库ID
     */
    @TableId
    private String inventoryLogId;
    /**
     * 采购单据ID
     */
    private String purchaseId;
    /**
     * 药品ID
     */
    private String medicinesId;
    /**
     * 入库数量
     */
    private Integer inventoryLogNum;
    /**
     * 批发价
     */
    private BigDecimal tradePrice;
    /**
     * 处方价
     */
    private BigDecimal prescriptionPrice;
    /**
     * 批发额
     */
    private BigDecimal tradeTotalAmount;
    /**
     * 处方额
     */
    private BigDecimal prescriptionTotalAmount;
    /**
     * 药品生产批次号
     */
    private String batchNumber;
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
     * 供应商ID
     */
    private String providerId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 创建者
     */
    private String createBy;

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
        InventoryLog other = (InventoryLog) that;
        return (this.getInventoryLogId() == null ? other.getInventoryLogId() == null : this.getInventoryLogId().equals(other.getInventoryLogId()))
                && (this.getPurchaseId() == null ? other.getPurchaseId() == null : this.getPurchaseId().equals(other.getPurchaseId()))
                && (this.getMedicinesId() == null ? other.getMedicinesId() == null : this.getMedicinesId().equals(other.getMedicinesId()))
                && (this.getInventoryLogNum() == null ? other.getInventoryLogNum() == null : this.getInventoryLogNum().equals(other.getInventoryLogNum()))
                && (this.getTradePrice() == null ? other.getTradePrice() == null : this.getTradePrice().equals(other.getTradePrice()))
                && (this.getPrescriptionPrice() == null ? other.getPrescriptionPrice() == null : this.getPrescriptionPrice().equals(other.getPrescriptionPrice()))
                && (this.getTradeTotalAmount() == null ? other.getTradeTotalAmount() == null : this.getTradeTotalAmount().equals(other.getTradeTotalAmount()))
                && (this.getPrescriptionTotalAmount() == null ? other.getPrescriptionTotalAmount() == null : this.getPrescriptionTotalAmount().equals(other.getPrescriptionTotalAmount()))
                && (this.getBatchNumber() == null ? other.getBatchNumber() == null : this.getBatchNumber().equals(other.getBatchNumber()))
                && (this.getMedicinesName() == null ? other.getMedicinesName() == null : this.getMedicinesName().equals(other.getMedicinesName()))
                && (this.getMedicinesType() == null ? other.getMedicinesType() == null : this.getMedicinesType().equals(other.getMedicinesType()))
                && (this.getPrescriptionType() == null ? other.getPrescriptionType() == null : this.getPrescriptionType().equals(other.getPrescriptionType()))
                && (this.getProducterId() == null ? other.getProducterId() == null : this.getProducterId().equals(other.getProducterId()))
                && (this.getConversion() == null ? other.getConversion() == null : this.getConversion().equals(other.getConversion()))
                && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
                && (this.getProviderId() == null ? other.getProviderId() == null : this.getProviderId().equals(other.getProviderId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInventoryLogId() == null) ? 0 : getInventoryLogId().hashCode());
        result = prime * result + ((getPurchaseId() == null) ? 0 : getPurchaseId().hashCode());
        result = prime * result + ((getMedicinesId() == null) ? 0 : getMedicinesId().hashCode());
        result = prime * result + ((getInventoryLogNum() == null) ? 0 : getInventoryLogNum().hashCode());
        result = prime * result + ((getTradePrice() == null) ? 0 : getTradePrice().hashCode());
        result = prime * result + ((getPrescriptionPrice() == null) ? 0 : getPrescriptionPrice().hashCode());
        result = prime * result + ((getTradeTotalAmount() == null) ? 0 : getTradeTotalAmount().hashCode());
        result = prime * result + ((getPrescriptionTotalAmount() == null) ? 0 : getPrescriptionTotalAmount().hashCode());
        result = prime * result + ((getBatchNumber() == null) ? 0 : getBatchNumber().hashCode());
        result = prime * result + ((getMedicinesName() == null) ? 0 : getMedicinesName().hashCode());
        result = prime * result + ((getMedicinesType() == null) ? 0 : getMedicinesType().hashCode());
        result = prime * result + ((getPrescriptionType() == null) ? 0 : getPrescriptionType().hashCode());
        result = prime * result + ((getProducterId() == null) ? 0 : getProducterId().hashCode());
        result = prime * result + ((getConversion() == null) ? 0 : getConversion().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getProviderId() == null) ? 0 : getProviderId().hashCode());
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
        sb.append(", inventoryLogId=").append(inventoryLogId);
        sb.append(", purchaseId=").append(purchaseId);
        sb.append(", medicinesId=").append(medicinesId);
        sb.append(", inventoryLogNum=").append(inventoryLogNum);
        sb.append(", tradePrice=").append(tradePrice);
        sb.append(", prescriptionPrice=").append(prescriptionPrice);
        sb.append(", tradeTotalAmount=").append(tradeTotalAmount);
        sb.append(", prescriptionTotalAmount=").append(prescriptionTotalAmount);
        sb.append(", batchNumber=").append(batchNumber);
        sb.append(", medicinesName=").append(medicinesName);
        sb.append(", medicinesType=").append(medicinesType);
        sb.append(", prescriptionType=").append(prescriptionType);
        sb.append(", producterId=").append(producterId);
        sb.append(", conversion=").append(conversion);
        sb.append(", unit=").append(unit);
        sb.append(", providerId=").append(providerId);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
