package com.aaa.core.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 生产厂家表
 * @TableName stock_producer
 */
@TableName(value ="stock_producer")
@Data
public class Producer implements Serializable {
    /**
     * 厂家ID
     */
    @TableId(type = IdType.AUTO)
    private Long producerId;

    /**
     * 厂家名称
     */
    private String producerName;

    /**
     * 厂家简码 搜索用
     */
    private String producerCode;

    /**
     * 厂家地址
     */
    private String producerAddress;

    /**
     * 厂家电话
     */
    private String producerTel;

    /**
     * 联系人
     */
    private String producerPerson;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 状态标志（0正常 1停用）sys_normal_disable
     */
    @TableLogic   //@TableLogic代表此字段用于逻辑删除
    private String status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")  //添加删除使用（前端）
    @DateTimeFormat(pattern = "yyyy-MM-dd") //请求时格式处理（后端）
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
        Producer other = (Producer) that;
        return (this.getProducerId() == null ? other.getProducerId() == null : this.getProducerId().equals(other.getProducerId()))
            && (this.getProducerName() == null ? other.getProducerName() == null : this.getProducerName().equals(other.getProducerName()))
            && (this.getProducerCode() == null ? other.getProducerCode() == null : this.getProducerCode().equals(other.getProducerCode()))
            && (this.getProducerAddress() == null ? other.getProducerAddress() == null : this.getProducerAddress().equals(other.getProducerAddress()))
            && (this.getProducerTel() == null ? other.getProducerTel() == null : this.getProducerTel().equals(other.getProducerTel()))
            && (this.getProducerPerson() == null ? other.getProducerPerson() == null : this.getProducerPerson().equals(other.getProducerPerson()))
            && (this.getKeywords() == null ? other.getKeywords() == null : this.getKeywords().equals(other.getKeywords()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProducerId() == null) ? 0 : getProducerId().hashCode());
        result = prime * result + ((getProducerName() == null) ? 0 : getProducerName().hashCode());
        result = prime * result + ((getProducerCode() == null) ? 0 : getProducerCode().hashCode());
        result = prime * result + ((getProducerAddress() == null) ? 0 : getProducerAddress().hashCode());
        result = prime * result + ((getProducerTel() == null) ? 0 : getProducerTel().hashCode());
        result = prime * result + ((getProducerPerson() == null) ? 0 : getProducerPerson().hashCode());
        result = prime * result + ((getKeywords() == null) ? 0 : getKeywords().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", producerId=").append(producerId);
        sb.append(", producerName=").append(producerName);
        sb.append(", producerCode=").append(producerCode);
        sb.append(", producerAddress=").append(producerAddress);
        sb.append(", producerTel=").append(producerTel);
        sb.append(", producerPerson=").append(producerPerson);
        sb.append(", keywords=").append(keywords);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
