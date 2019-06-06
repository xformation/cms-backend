package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Document(indexName = "latefee")
public class LateFee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "assign_late_fee", nullable = false)
    private String assignLateFee;

    @NotNull
    @Column(name = "late_fee_days", nullable = false)
    private Integer lateFeeDays;

    @Column(name = "fixed")
    private String fixed;

    @Column(name = "percentage")
    private String percentage;

    @Column(name = "fixed_charges")
    private Long fixedCharges;

    @Column(name = "percent_charges")
    private String percentCharges;

    @Column(name = "late_fee_one_time")
    private String lateFeeOneTime;

    @Column(name = "late_fee_repeat_days")
    private Integer lateFeeRepeatDays;

    @ManyToOne
    @JsonIgnoreProperties("")
    private College college;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Branch branch;

    @ManyToOne
    @JsonIgnoreProperties("")
    private AcademicYear academicYear;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Term term;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignLateFee() {
        return assignLateFee;
    }

    public LateFee assignLateFee(String assignLateFee) {
        this.assignLateFee = assignLateFee;
        return this;
    }

    public void setAssignLateFee(String assignLateFee) {
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

    public String getFixed() {
        return fixed;
    }

    public LateFee fixed(String fixed) {
        this.fixed = fixed;
        return this;
    }

    public void setFixed(String fixed) {
        this.fixed = fixed;
    }

    public String getPercentage() {
        return percentage;
    }

    public LateFee percentage(String percentage) {
        this.percentage = percentage;
        return this;
    }

    public void setPercentage(String percentage) {
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

    public String getLateFeeOneTime() {
        return lateFeeOneTime;
    }

    public LateFee lateFeeOneTime(String lateFeeOneTime) {
        this.lateFeeOneTime = lateFeeOneTime;
        return this;
    }

    public void setLateFeeOneTime(String lateFeeOneTime) {
        this.lateFeeOneTime = lateFeeOneTime;
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

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public LateFee academicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
        return this;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Term getTerm() {
        return term;
    }

    public LateFee term(Term term) {
        this.term = term;
        return this;
    }

    public void setTerm(Term term) {
        this.term = term;
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
            ", percentCharges='" + getPercentCharges() + "'" +
            ", lateFeeOneTime='" + getLateFeeOneTime() + "'" +
            ", lateFeeRepeatDays=" + getLateFeeRepeatDays() +
            "}";
    }
}
