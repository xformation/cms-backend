package com.synectiks.cms.domain;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.synectiks.cms.domain.enumeration.Frequency;

/**
 * A DueDate.
 */
@Entity
@Table(name = "due_date")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@Document(indexName = "duedate")
public class DueDate implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @NotNull
    @Column(name = "installments", nullable = false)
    private Integer installments;

    @Column(name = "day_desc")
    private String dayDesc;

    @Column(name = "payment_day")
    private Integer paymentDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency")
    private Frequency frequency;

    @Column(name = "college_id")
    private Long collegeId;

    @Column(name = "branch_id")
    private Long branchId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public DueDate paymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getInstallments() {
        return installments;
    }

    public DueDate installments(Integer installments) {
        this.installments = installments;
        return this;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public String getDayDesc() {
        return dayDesc;
    }

    public DueDate dayDesc(String dayDesc) {
        this.dayDesc = dayDesc;
        return this;
    }

    public void setDayDesc(String dayDesc) {
        this.dayDesc = dayDesc;
    }

    public Integer getPaymentDay() {
        return paymentDay;
    }

    public DueDate paymentDay(Integer paymentDay) {
        this.paymentDay = paymentDay;
        return this;
    }

    public void setPaymentDay(Integer paymentDay) {
        this.paymentDay = paymentDay;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public DueDate frequency(Frequency frequency) {
        this.frequency = frequency;
        return this;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public DueDate collegeId(Long collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public DueDate branchId(Long branchId) {
        this.branchId = branchId;
        return this;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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
        DueDate dueDate = (DueDate) o;
        if (dueDate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dueDate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DueDate{" +
            "id=" + getId() +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            ", installments=" + getInstallments() +
            ", dayDesc='" + getDayDesc() + "'" +
            ", paymentDay=" + getPaymentDay() +
            ", frequency='" + getFrequency() + "'" +
            ", collegeId=" + getCollegeId() +
            ", branchId=" + getBranchId() +
            "}";
    }
}
