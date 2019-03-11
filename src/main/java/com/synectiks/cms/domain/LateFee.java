package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.Status;

import com.synectiks.cms.domain.enumeration.Frequency;

/**
 * A LateFee.
 */
@Entity
@Table(name = "late_fee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "latefee")
public class LateFee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "assign_late_fee", nullable = false)
    private Status assignLateFee;

    @NotNull
    @Column(name = "late_fee_days", nullable = false)
    private Integer lateFeeDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "fixed")
    private Status fixed;

    @Enumerated(EnumType.STRING)
    @Column(name = "percentage")
    private Status percentage;

    @Column(name = "fixed_charges")
    private Long fixedCharges;

    @Column(name = "percent_charges")
    private Long percentCharges;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "late_fee_assignment_frequency", nullable = false)
    private Frequency lateFeeAssignmentFrequency;

    @ManyToOne
    @JsonIgnoreProperties("")
    private College college;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Branch branch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getAssignLateFee() {
        return assignLateFee;
    }

    public LateFee assignLateFee(Status assignLateFee) {
        this.assignLateFee = assignLateFee;
        return this;
    }

    public void setAssignLateFee(Status assignLateFee) {
        this.assignLateFee = assignLateFee;
    }

    public Integer getLateFeeDays() {
        return lateFeeDays;
    }

    public LateFee lateFeeDays(Integer lateFeeDays) {
        this.lateFeeDays = lateFeeDays;
        return this;
    }

    public void setLateFeeDays(Integer lateFeeDays) {
        this.lateFeeDays = lateFeeDays;
    }

    public Status getFixed() {
        return fixed;
    }

    public LateFee fixed(Status fixed) {
        this.fixed = fixed;
        return this;
    }

    public void setFixed(Status fixed) {
        this.fixed = fixed;
    }

    public Status getPercentage() {
        return percentage;
    }

    public LateFee percentage(Status percentage) {
        this.percentage = percentage;
        return this;
    }

    public void setPercentage(Status percentage) {
        this.percentage = percentage;
    }

    public Long getFixedCharges() {
        return fixedCharges;
    }

    public LateFee fixedCharges(Long fixedCharges) {
        this.fixedCharges = fixedCharges;
        return this;
    }

    public void setFixedCharges(Long fixedCharges) {
        this.fixedCharges = fixedCharges;
    }

    public Long getPercentCharges() {
        return percentCharges;
    }

    public LateFee percentCharges(Long percentCharges) {
        this.percentCharges = percentCharges;
        return this;
    }

    public void setPercentCharges(Long percentCharges) {
        this.percentCharges = percentCharges;
    }

    public Frequency getLateFeeAssignmentFrequency() {
        return lateFeeAssignmentFrequency;
    }

    public LateFee lateFeeAssignmentFrequency(Frequency lateFeeAssignmentFrequency) {
        this.lateFeeAssignmentFrequency = lateFeeAssignmentFrequency;
        return this;
    }

    public void setLateFeeAssignmentFrequency(Frequency lateFeeAssignmentFrequency) {
        this.lateFeeAssignmentFrequency = lateFeeAssignmentFrequency;
    }

    public College getCollege() {
        return college;
    }

    public LateFee college(College college) {
        this.college = college;
        return this;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Branch getBranch() {
        return branch;
    }

    public LateFee branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LateFee lateFee = (LateFee) o;
        if (lateFee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lateFee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LateFee{" +
            "id=" + getId() +
            ", assignLateFee='" + getAssignLateFee() + "'" +
            ", lateFeeDays=" + getLateFeeDays() +
            ", fixed='" + getFixed() + "'" +
            ", percentage='" + getPercentage() + "'" +
            ", fixedCharges=" + getFixedCharges() +
            ", percentCharges=" + getPercentCharges() +
            ", lateFeeAssignmentFrequency='" + getLateFeeAssignmentFrequency() + "'" +
            "}";
    }
}
