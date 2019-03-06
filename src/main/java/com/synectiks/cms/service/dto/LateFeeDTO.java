package com.synectiks.cms.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Frequency;

/**
 * A DTO for the LateFee entity.
 */
public class LateFeeDTO implements Serializable {

    private Long id;

    @NotNull
    private Status assignLateFee;

    @NotNull
    private Integer lateFeeDays;

    private Status fixed;

    private Status percentage;

    private Long fixedCharges;

    private Long percentCharges;

    @NotNull
    private Frequency lateFeeAssignmentFrequency;


    private Long collegeId;

    private Long branchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getAssignLateFee() {
        return assignLateFee;
    }

    public void setAssignLateFee(Status assignLateFee) {
        this.assignLateFee = assignLateFee;
    }

    public Integer getLateFeeDays() {
        return lateFeeDays;
    }

    public void setLateFeeDays(Integer lateFeeDays) {
        this.lateFeeDays = lateFeeDays;
    }

    public Status getFixed() {
        return fixed;
    }

    public void setFixed(Status fixed) {
        this.fixed = fixed;
    }

    public Status getPercentage() {
        return percentage;
    }

    public void setPercentage(Status percentage) {
        this.percentage = percentage;
    }

    public Long getFixedCharges() {
        return fixedCharges;
    }

    public void setFixedCharges(Long fixedCharges) {
        this.fixedCharges = fixedCharges;
    }

    public Long getPercentCharges() {
        return percentCharges;
    }

    public void setPercentCharges(Long percentCharges) {
        this.percentCharges = percentCharges;
    }

    public Frequency getLateFeeAssignmentFrequency() {
        return lateFeeAssignmentFrequency;
    }

    public void setLateFeeAssignmentFrequency(Frequency lateFeeAssignmentFrequency) {
        this.lateFeeAssignmentFrequency = lateFeeAssignmentFrequency;
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
            ", percentCharges=" + getPercentCharges() +
            ", lateFeeAssignmentFrequency='" + getLateFeeAssignmentFrequency() + "'" +
            ", college=" + getCollegeId() +
            ", branch=" + getBranchId() +
            "}";
    }
}
