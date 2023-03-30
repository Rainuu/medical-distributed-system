package com.aaa.core.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 药品信息表
 * @TableName stock_medicines
 */
@TableName(value ="stock_medicines")
@Data
public class Medicines implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long medicinesId;

    /**
     * 药品编号
     */
    private String medicinesNumber;

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
     * 处方价格
     */
    private BigDecimal prescriptionPrice;

    /**
     * 单位（g/条）
     */
    private String unit;

    /**
     * 换算量
     */
    private Integer conversion;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 生产厂家ID
     */
    private String producterId;

    /**
     * 药品状态0正常0停用 sys_dict_data表 sys_normal_disable
     */
    @TableLogic
    private String status;

    /**
     * 库存量
     */
    private Integer medicinesStockNum;

    /**
     * 预警值
     */
    private Integer medicinesStockDangerNum;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 删除状态0正常0删除 要有重新导入功能
     */
    private String delFlag;


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
        Medicines other = (Medicines) that;
        return (this.getMedicinesId() == null ? other.getMedicinesId() == null : this.getMedicinesId().equals(other.getMedicinesId()))
            && (this.getMedicinesNumber() == null ? other.getMedicinesNumber() == null : this.getMedicinesNumber().equals(other.getMedicinesNumber()))
            && (this.getMedicinesName() == null ? other.getMedicinesName() == null : this.getMedicinesName().equals(other.getMedicinesName()))
            && (this.getMedicinesType() == null ? other.getMedicinesType() == null : this.getMedicinesType().equals(other.getMedicinesType()))
            && (this.getPrescriptionType() == null ? other.getPrescriptionType() == null : this.getPrescriptionType().equals(other.getPrescriptionType()))
            && (this.getPrescriptionPrice() == null ? other.getPrescriptionPrice() == null : this.getPrescriptionPrice().equals(other.getPrescriptionPrice()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getConversion() == null ? other.getConversion() == null : this.getConversion().equals(other.getConversion()))
            && (this.getKeywords() == null ? other.getKeywords() == null : this.getKeywords().equals(other.getKeywords()))
            && (this.getProducterId() == null ? other.getProducterId() == null : this.getProducterId().equals(other.getProducterId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getMedicinesStockNum() == null ? other.getMedicinesStockNum() == null : this.getMedicinesStockNum().equals(other.getMedicinesStockNum()))
            && (this.getMedicinesStockDangerNum() == null ? other.getMedicinesStockDangerNum() == null : this.getMedicinesStockDangerNum().equals(other.getMedicinesStockDangerNum()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMedicinesId() == null) ? 0 : getMedicinesId().hashCode());
        result = prime * result + ((getMedicinesNumber() == null) ? 0 : getMedicinesNumber().hashCode());
        result = prime * result + ((getMedicinesName() == null) ? 0 : getMedicinesName().hashCode());
        result = prime * result + ((getMedicinesType() == null) ? 0 : getMedicinesType().hashCode());
        result = prime * result + ((getPrescriptionType() == null) ? 0 : getPrescriptionType().hashCode());
        result = prime * result + ((getPrescriptionPrice() == null) ? 0 : getPrescriptionPrice().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getConversion() == null) ? 0 : getConversion().hashCode());
        result = prime * result + ((getKeywords() == null) ? 0 : getKeywords().hashCode());
        result = prime * result + ((getProducterId() == null) ? 0 : getProducterId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getMedicinesStockNum() == null) ? 0 : getMedicinesStockNum().hashCode());
        result = prime * result + ((getMedicinesStockDangerNum() == null) ? 0 : getMedicinesStockDangerNum().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", medicinesId=").append(medicinesId);
        sb.append(", medicinesNumber=").append(medicinesNumber);
        sb.append(", medicinesName=").append(medicinesName);
        sb.append(", medicinesType=").append(medicinesType);
        sb.append(", prescriptionType=").append(prescriptionType);
        sb.append(", prescriptionPrice=").append(prescriptionPrice);
        sb.append(", unit=").append(unit);
        sb.append(", conversion=").append(conversion);
        sb.append(", keywords=").append(keywords);
        sb.append(", producterId=").append(producterId);
        sb.append(", status=").append(status);
        sb.append(", medicinesStockNum=").append(medicinesStockNum);
        sb.append(", medicinesStockDangerNum=").append(medicinesStockDangerNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
