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
 *
 * @TableName his_check_result
 */
@TableName(value ="his_check_result")
@Data
public class CheckResult implements Serializable {
    /**
     * 处方检查项ID
     */
    @TableId
    private String cocId;

    /**
     * 检查项目ID
     */
    private Integer checkItemId;

    /**
     * 检查项目名称
     */
    private String checkItemName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 挂号id
     */
    private String regId;

    /**
     * 检查结果描述
     */
    private String resultMsg;

    /**
     * 检查结果扫描附件[json表示]
     */
    private Object resultImg;

    /**
     * 患者ID
     */
    private String patientId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 是否录入检查结果0 检测中  1 检测完成  字典表 his_check_result_status
     */
    private String resultStatus;

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
        CheckResult other = (CheckResult) that;
        return (this.getCocId() == null ? other.getCocId() == null : this.getCocId().equals(other.getCocId()))
            && (this.getCheckItemId() == null ? other.getCheckItemId() == null : this.getCheckItemId().equals(other.getCheckItemId()))
            && (this.getCheckItemName() == null ? other.getCheckItemName() == null : this.getCheckItemName().equals(other.getCheckItemName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getRegId() == null ? other.getRegId() == null : this.getRegId().equals(other.getRegId()))
            && (this.getResultMsg() == null ? other.getResultMsg() == null : this.getResultMsg().equals(other.getResultMsg()))
            && (this.getResultImg() == null ? other.getResultImg() == null : this.getResultImg().equals(other.getResultImg()))
            && (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getPatientName() == null ? other.getPatientName() == null : this.getPatientName().equals(other.getPatientName()))
            && (this.getResultStatus() == null ? other.getResultStatus() == null : this.getResultStatus().equals(other.getResultStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCocId() == null) ? 0 : getCocId().hashCode());
        result = prime * result + ((getCheckItemId() == null) ? 0 : getCheckItemId().hashCode());
        result = prime * result + ((getCheckItemName() == null) ? 0 : getCheckItemName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getRegId() == null) ? 0 : getRegId().hashCode());
        result = prime * result + ((getResultMsg() == null) ? 0 : getResultMsg().hashCode());
        result = prime * result + ((getResultImg() == null) ? 0 : getResultImg().hashCode());
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getPatientName() == null) ? 0 : getPatientName().hashCode());
        result = prime * result + ((getResultStatus() == null) ? 0 : getResultStatus().hashCode());
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
        sb.append(", cocId=").append(cocId);
        sb.append(", checkItemId=").append(checkItemId);
        sb.append(", checkItemName=").append(checkItemName);
        sb.append(", price=").append(price);
        sb.append(", regId=").append(regId);
        sb.append(", resultMsg=").append(resultMsg);
        sb.append(", resultImg=").append(resultImg);
        sb.append(", patientId=").append(patientId);
        sb.append(", patientName=").append(patientName);
        sb.append(", resultStatus=").append(resultStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
