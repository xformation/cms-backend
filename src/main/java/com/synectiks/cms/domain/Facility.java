package com.synectiks.cms.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.utils.IESEntity;

/**
 * A Facility.
 */
@Entity
@Table(name = "facility")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@Document(indexName = "facility")
public class Facility implements Serializable, IESEntity {

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
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate startDate;

    @Column(name = "end_date")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate endDate;

    @Column(name = "suspand_start_date")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate suspandStartDate;

    @Column(name = "suspand_end_date")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate suspandEndDate;

    @ManyToOne
    @JsonIgnoreProperties("")
    private AcademicYear academicYear;

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

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public Facility academicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
        return this;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Branch getBranch() {
        return branch;
    }

    public Facility branch(Branch branch) {
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
        Facility facility = (Facility) o;
        if (facility.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), facility.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
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
            "}";
    }
}
