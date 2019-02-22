package com.synectiks.cms.graphql.types.FeeDetails;

import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.StudentTypeEnum;

import java.util.Objects;

public class AbstractFeeDetailsInput {
    private Long id;
    private String feeParticularsName;
    private String feeParticularDesc;
    private StudentTypeEnum  studentType;
    private Gender gender;
    private Long amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeeParticularsName() {
        return feeParticularsName;
    }

    public void setFeeParticularsName(String feeParticularsName) {
        this.feeParticularsName = feeParticularsName;
    }

    public String getFeeParticularDesc() {
        return feeParticularDesc;
    }

    public void setFeeParticularDesc(String feeParticularDesc) {
        this.feeParticularDesc = feeParticularDesc;
    }

    public StudentTypeEnum getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentTypeEnum studentType) {
        this.studentType = studentType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractFeeDetailsInput)) return false;
        AbstractFeeDetailsInput that = (AbstractFeeDetailsInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getFeeParticularsName(), that.getFeeParticularsName()) &&
            Objects.equals(getFeeParticularDesc(), that.getFeeParticularDesc()) &&
            getStudentType() == that.getStudentType() &&
            getGender() == that.getGender() &&
            Objects.equals(getAmount(), that.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFeeParticularsName(), getFeeParticularDesc(), getStudentType(), getGender(), getAmount());
    }

    @Override
    public String toString() {
        return "AbstractFeeDetailsInput{" +
            "id=" + id +
            ", feeParticularsName='" + feeParticularsName + '\'' +
            ", feeParticularDesc='" + feeParticularDesc + '\'' +
            ", studentType=" + studentType +
            ", gender=" + gender +
            ", amount=" + amount +
            '}';
    }
}
