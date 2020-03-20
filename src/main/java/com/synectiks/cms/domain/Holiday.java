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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synectiks.cms.domain.enumeration.Status;

/**
 * A Holiday.
 */
@Entity
@Table(name = "holiday")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Holiday implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "holiday_desc", nullable = false)
    private String holidayDesc;

    @NotNull
    @Column(name = "holiday_date", nullable = false)
    private LocalDate holidayDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "holiday_status", nullable = false)
    private Status holidayStatus;

    @ManyToOne
    @JsonIgnoreProperties("holidays")
    private AcademicYear academicyear;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolidayDesc() {
        return holidayDesc;
    }

    public Holiday holidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
        return this;
    }

    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public Holiday holidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
        return this;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    public Status getHolidayStatus() {
        return holidayStatus;
    }

    public Holiday holidayStatus(Status holidayStatus) {
        this.holidayStatus = holidayStatus;
        return this;
    }

    public void setHolidayStatus(Status holidayStatus) {
        this.holidayStatus = holidayStatus;
    }

    public AcademicYear getAcademicyear() {
        return academicyear;
    }

    public Holiday academicyear(AcademicYear academicYear) {
        this.academicyear = academicYear;
        return this;
    }

    public void setAcademicyear(AcademicYear academicYear) {
        this.academicyear = academicYear;
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
        Holiday holiday = (Holiday) o;
        if (holiday.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), holiday.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Holiday{" +
            "id=" + getId() +
            ", holidayDesc='" + getHolidayDesc() + "'" +
            ", holidayDate='" + getHolidayDate() + "'" +
            ", holidayStatus='" + getHolidayStatus() + "'" +
            "}";
    }
}
