package com.synectiks.cms.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.StudentTypeEnum;
import com.synectiks.cms.domain.enumeration.Gender;

/**
 * A DTO for the FeeDetails entity.
 */
public class FeeDetailsDTO implements Serializable {

    private Long id;

    @NotNull
    private String feeParticularsName;

    @NotNull
    private String feeParticularDesc;

    @NotNull
    private StudentTypeEnum studentType;

    @NotNull
    private Gender gender;

    @NotNull
    private Long amount;


    private Long feeCategoryId;

    private Long batchId;

    private Long facilityId;

    private Long transportRouteId;

    private Long collegeId;

    private Long departmentId;

    private Long branchId;

    private Long academicYearId;

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

    public Long getFeeCategoryId() {
        return feeCategoryId;
    }

    public void setFeeCategoryId(Long feeCategoryId) {
        this.feeCategoryId = feeCategoryId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public Long getTransportRouteId() {
        return transportRouteId;
    }

    public void setTransportRouteId(Long transportRouteId) {
        this.transportRouteId = transportRouteId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeeDetailsDTO feeDetailsDTO = (FeeDetailsDTO) o;
        if (feeDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feeDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeeDetailsDTO{" +
            "id=" + getId() +
            ", feeParticularsName='" + getFeeParticularsName() + "'" +
            ", feeParticularDesc='" + getFeeParticularDesc() + "'" +
            ", studentType='" + getStudentType() + "'" +
            ", gender='" + getGender() + "'" +
            ", amount=" + getAmount() +
            ", feeCategory=" + getFeeCategoryId() +
            ", batch=" + getBatchId() +
            ", facility=" + getFacilityId() +
            ", transportRoute=" + getTransportRouteId() +
            ", college=" + getCollegeId() +
            ", department=" + getDepartmentId() +
            ", branch=" + getBranchId() +
            ", academicYear=" + getAcademicYearId() +
            "}";
    }
}
