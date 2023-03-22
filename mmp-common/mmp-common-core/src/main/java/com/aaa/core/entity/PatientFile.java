package com.aaa.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName his_patient_file
 */
@TableName(value ="his_patient_file")
@Data
public class PatientFile implements Serializable {
    /**
     * 患者id
     */
    @TableId
    private String patientId;

    /**
     * 紧急联系人
     */
    private String emergencyContactName;

    /**
     * 紧急联系人电话
     */
    private String emergencyContactPhone;

    /**
     * 爸爸,妈妈,儿子,女儿,亲戚,朋友
     */
    private String emergencyContactRelation;

    /**
     * 左耳听力 正常  耳聋
     */
    private String leftEarHearing;

    /**
     * 右耳听力 正常  耳聋
     */
    private String rightEarHearing;

    /**
     * 左眼视力
     */
    private BigDecimal leftVision;

    /**
     * 右眼视力
     */
    private BigDecimal rightVision;

    /**
     * 身高
     */
    private BigDecimal height;

    /**
     * 体重
     */
    private BigDecimal weight;

    /**
     * 血型 A  B  AB  O    R-阴 RH-阳
     */
    private String bloodType;

    /**
     * 个人史
     */
    private String personalInfo;

    /**
     * 家族史
     */
    private String familyInfo;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
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
        PatientFile other = (PatientFile) that;
        return (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getEmergencyContactName() == null ? other.getEmergencyContactName() == null : this.getEmergencyContactName().equals(other.getEmergencyContactName()))
            && (this.getEmergencyContactPhone() == null ? other.getEmergencyContactPhone() == null : this.getEmergencyContactPhone().equals(other.getEmergencyContactPhone()))
            && (this.getEmergencyContactRelation() == null ? other.getEmergencyContactRelation() == null : this.getEmergencyContactRelation().equals(other.getEmergencyContactRelation()))
            && (this.getLeftEarHearing() == null ? other.getLeftEarHearing() == null : this.getLeftEarHearing().equals(other.getLeftEarHearing()))
            && (this.getRightEarHearing() == null ? other.getRightEarHearing() == null : this.getRightEarHearing().equals(other.getRightEarHearing()))
            && (this.getLeftVision() == null ? other.getLeftVision() == null : this.getLeftVision().equals(other.getLeftVision()))
            && (this.getRightVision() == null ? other.getRightVision() == null : this.getRightVision().equals(other.getRightVision()))
            && (this.getHeight() == null ? other.getHeight() == null : this.getHeight().equals(other.getHeight()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getBloodType() == null ? other.getBloodType() == null : this.getBloodType().equals(other.getBloodType()))
            && (this.getPersonalInfo() == null ? other.getPersonalInfo() == null : this.getPersonalInfo().equals(other.getPersonalInfo()))
            && (this.getFamilyInfo() == null ? other.getFamilyInfo() == null : this.getFamilyInfo().equals(other.getFamilyInfo()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getEmergencyContactName() == null) ? 0 : getEmergencyContactName().hashCode());
        result = prime * result + ((getEmergencyContactPhone() == null) ? 0 : getEmergencyContactPhone().hashCode());
        result = prime * result + ((getEmergencyContactRelation() == null) ? 0 : getEmergencyContactRelation().hashCode());
        result = prime * result + ((getLeftEarHearing() == null) ? 0 : getLeftEarHearing().hashCode());
        result = prime * result + ((getRightEarHearing() == null) ? 0 : getRightEarHearing().hashCode());
        result = prime * result + ((getLeftVision() == null) ? 0 : getLeftVision().hashCode());
        result = prime * result + ((getRightVision() == null) ? 0 : getRightVision().hashCode());
        result = prime * result + ((getHeight() == null) ? 0 : getHeight().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getBloodType() == null) ? 0 : getBloodType().hashCode());
        result = prime * result + ((getPersonalInfo() == null) ? 0 : getPersonalInfo().hashCode());
        result = prime * result + ((getFamilyInfo() == null) ? 0 : getFamilyInfo().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", patientId=").append(patientId);
        sb.append(", emergencyContactName=").append(emergencyContactName);
        sb.append(", emergencyContactPhone=").append(emergencyContactPhone);
        sb.append(", emergencyContactRelation=").append(emergencyContactRelation);
        sb.append(", leftEarHearing=").append(leftEarHearing);
        sb.append(", rightEarHearing=").append(rightEarHearing);
        sb.append(", leftVision=").append(leftVision);
        sb.append(", rightVision=").append(rightVision);
        sb.append(", height=").append(height);
        sb.append(", weight=").append(weight);
        sb.append(", bloodType=").append(bloodType);
        sb.append(", personalInfo=").append(personalInfo);
        sb.append(", familyInfo=").append(familyInfo);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
