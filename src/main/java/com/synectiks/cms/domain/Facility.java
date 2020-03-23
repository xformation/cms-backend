package com.synectiks.cms.domain;
import java.io.Serializable;
import java.time.LocalDate;

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

import com.synectiks.cms.domain.enumeration.Status;

/**
 * A Facility.
 */
@Entity
@Table(name = "facility")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Facility implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "suspand_start_date")
    private LocalDate suspandStartDate;

    @Column(name = "suspand_end_date")
    private LocalDate suspandEndDate;

    @Column(name = "academic_year_id")
    private Long academicYearId;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "amount")
    private Long amount;

    @javax.persistence.Transient
    private String branchName;
    
    @javax.persistence.Transient
    private String academicYear;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Facility name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public Facility status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Facility startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Facility endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getSuspandStartDate() {
        return suspandStartDate;
    }

    public Facility suspandStartDate(LocalDate suspandStartDate) {
        this.suspandStartDate = suspandStartDate;
        return this;
    }

    public void setSuspandStartDate(LocalDate suspandStartDate) {
        this.suspandStartDate = suspandStartDate;
    }

    public LocalDate getSuspandEndDate() {
        return suspandEndDate;
    }

    public Facility suspandEndDate(LocalDate suspandEndDate) {
        this.suspandEndDate = suspandEndDate;
        return this;
    }

    public void setSuspandEndDate(LocalDate suspandEndDate) {
        this.suspandEndDate = suspandEndDate;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public Facility academicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
        return this;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public Facility branchId(Long branchId) {
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
        if (!(o instanceof Facility)) {
            return false;
        }
        return id != null && id.equals(((Facility) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Facility{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", suspandStartDate='" + getSuspandStartDate() + "'" +
            ", suspandEndDate='" + getSuspandEndDate() + "'" +
            ", academicYearId=" + getAcademicYearId() +
            ", branchId=" + getBranchId() +
            "}";
    }

    @javax.persistence.Transient
	public String getBranchName() {
		return branchName;
	}

    @javax.persistence.Transient
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

    @javax.persistence.Transient
	public String getAcademicYear() {
		return academicYear;
	}

    @javax.persistence.Transient
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
