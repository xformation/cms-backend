package com.synectiks.cms.graphql.types.LateFee;

import com.synectiks.cms.domain.enumeration.Frequency;
import com.synectiks.cms.domain.enumeration.Status;

import java.util.Objects;

public class AbstractLateFeeInput {
    private Long id;
    private Status assignLateFee;
    private Integer lateFeeDays;
    private Status fixed;
    private Status percentage;
    private Long fixedCharges;
    private Long percentCharges;
    private Frequency lateFeeAssignmentFrequency;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractLateFeeInput)) return false;
        AbstractLateFeeInput that = (AbstractLateFeeInput) o;
        return Objects.equals(getId(), that.getId()) &&
            getAssignLateFee() == that.getAssignLateFee() &&
            Objects.equals(getLateFeeDays(), that.getLateFeeDays()) &&
            getFixed() == that.getFixed() &&
            getPercentage() == that.getPercentage() &&
            Objects.equals(getFixedCharges(), that.getFixedCharges()) &&
            Objects.equals(getPercentCharges(), that.getPercentCharges()) &&
            getLateFeeAssignmentFrequency() == that.getLateFeeAssignmentFrequency();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAssignLateFee(), getLateFeeDays(), getFixed(), getPercentage(), getFixedCharges(), getPercentCharges(), getLateFeeAssignmentFrequency());
    }

    @Override
    public String toString() {
        return "AbstractLateFeeInput{" +
            "id=" + id +
            ", assignLateFee=" + assignLateFee +
            ", lateFeeDays=" + lateFeeDays +
            ", fixed=" + fixed +
            ", percentage=" + percentage +
            ", fixedCharges=" + fixedCharges +
            ", percentCharges=" + percentCharges +
            ", lateFeeAssignmentFrequency=" + lateFeeAssignmentFrequency +
            '}';
    }
}
