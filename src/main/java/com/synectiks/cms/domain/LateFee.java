package com.synectiks.cms.domain;


import com.synectiks.cms.utils.IESEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A LateFee.
 */
@Entity
@Table(name = "late_fee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LateFee implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "is_auto_late_fee", nullable = false)
    private String isAutoLateFee;

    @Column(name = "late_fee_days")
    private Integer lateFeeDays;

    @Column(name = "charge_type")
    private String chargeType;

    @Column(name = "fixed_charges")
    private Long fixedCharges;

    @Column(name = "percent_charges")
    private String percentCharges;

    @Column(name = "late_fee_frequency")
    private String lateFeeFrequency;

    @Column(name = "late_fee_repeat_days")
    private Integer lateFeeRepeatDays;

    @Column(name = "college_id")
    private Long collegeId;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "academic_year_id")
    private Long academicYearId;

    @Column(name = "term_id")
    private Long termId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsAutoLateFee() {
        return isAutoLateFee;
    }

    public LateFee isAutoLateFee(String isAutoLateFee) {
        this.isAutoLateFee = isAutoLateFee;
        return this;
    }

    public void setIsAutoLateFee(String isAutoLateFee) {
        this.isAutoLateFee = isAutoLateFee;
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

    public String getChargeType() {
        return chargeType;
    }

    public LateFee chargeType(String chargeType) {
        this.chargeType = chargeType;
        return this;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
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

    public String getPercentCharges() {
        return percentCharges;
    }

    public LateFee percentCharges(String percentCharges) {
        this.percentCharges = percentCharges;
        return this;
    }

    public void setPercentCharges(String percentCharges) {
        this.percentCharges = percentCharges;
    }

    public String getLateFeeFrequency() {
        return lateFeeFrequency;
    }

    public LateFee lateFeeFrequency(String lateFeeFrequency) {
        this.lateFeeFrequency = lateFeeFrequency;
        return this;
    }

    public void setLateFeeFrequency(String lateFeeFrequency) {
        this.lateFeeFrequency = lateFeeFrequency;
    }

    public Integer getLateFeeRepeatDays() {
        return lateFeeRepeatDays;
    }

    public LateFee lateFeeRepeatDays(Integer lateFeeRepeatDays) {
        this.lateFeeRepeatDays = lateFeeRepeatDays;
        return this;
    }

    public void setLateFeeRepeatDays(Integer lateFeeRepeatDays) {
        this.lateFeeRepeatDays = lateFeeRepeatDays;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public LateFee collegeId(Long collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public LateFee branchId(Long branchId) {
        this.branchId = branchId;
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public LateFee academicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
        return this;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Long getTermId() {
        return termId;
    }

    public LateFee termId(Long termId) {
        this.termId = termId;
        return this;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
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
            ", isAutoLateFee='" + getIsAutoLateFee() + "'" +
            ", lateFeeDays=" + getLateFeeDays() +
            ", chargeType='" + getChargeType() + "'" +
            ", fixedCharges=" + getFixedCharges() +
            ", percentCharges='" + getPercentCharges() + "'" +
            ", lateFeeFrequency='" + getLateFeeFrequency() + "'" +
            ", lateFeeRepeatDays=" + getLateFeeRepeatDays() +
            ", collegeId=" + getCollegeId() +
            ", branchId=" + getBranchId() +
            ", academicYearId=" + getAcademicYearId() +
            ", termId=" + getTermId() +
            "}";
    }
}
