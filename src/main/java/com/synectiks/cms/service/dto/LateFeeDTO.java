package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LateFee entity.
 */
public class LateFeeDTO implements Serializable {

    private Long id;

    @NotNull
    private String assignLateFee;

    @NotNull
    private Integer lateFeeDays;

    private String fixed;

    private String percentage;

    private Long fixedCharges;

    private String percentCharges;

    private String lateFeeOneTime;

    private Integer lateFeeRepeatDays;

    private Long collegeId;

    private Long branchId;

    private Long academicYearId;

    private Long termId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignLateFee() {
        return assignLateFee;
    }

    public void setAssignLateFee(String assignLateFee) {
        this.assignLateFee = assignLateFee;
    }

    public Integer getLateFeeDays() {
        return lateFeeDays;
    }

    public void setLateFeeDays(Integer lateFeeDays) {
        this.lateFeeDays = lateFeeDays;
    }

    public String getFixed() {
        return fixed;
    }

    public void setFixed(String fixed) {
        this.fixed = fixed;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Long getFixedCharges() {
        return fixedCharges;
    }

    public void setFixedCharges(Long fixedCharges) {
        this.fixedCharges = fixedCharges;
    }

    public String getPercentCharges() {
        return percentCharges;
    }

    public void setPercentCharges(String percentCharges) {
        this.percentCharges = percentCharges;
    }

    public String getLateFeeOneTime() {
        return lateFeeOneTime;
    }

    public void setLateFeeOneTime(String lateFeeOneTime) {
        this.lateFeeOneTime = lateFeeOneTime;
    }

    public Integer getLateFeeRepeatDays() {
        return lateFeeRepeatDays;
    }

    public void setLateFeeRepeatDays(Integer lateFeeRepeatDays) {
        this.lateFeeRepeatDays = lateFeeRepeatDays;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
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

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LateFeeDTO lateFeeDTO = (LateFeeDTO) o;
        if (lateFeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lateFeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LateFeeDTO{" +
            "id=" + getId() +
            ", assignLateFee='" + getAssignLateFee() + "'" +
            ", lateFeeDays=" + getLateFeeDays() +
            ", fixed='" + getFixed() + "'" +
            ", percentage='" + getPercentage() + "'" +
            ", fixedCharges=" + getFixedCharges() +
            ", percentCharges='" + getPercentCharges() + "'" +
            ", lateFeeOneTime='" + getLateFeeOneTime() + "'" +
            ", lateFeeRepeatDays=" + getLateFeeRepeatDays() +
            ", college=" + getCollegeId() +
            ", branch=" + getBranchId() +
            ", academicYear=" + getAcademicYearId() +
            ", term=" + getTermId() +
            "}";
    }
}
