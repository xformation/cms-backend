package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.LecStatusEnum;

/**
 * A Lecture.
 */
@Entity
@Table(name = "lecture")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "lecture")
public class Lecture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "lec_date", nullable = false)
    private LocalDate lecDate;

    @NotNull
    @Column(name = "last_updated_on", nullable = false)
    private LocalDate lastUpdatedOn;

    @NotNull
    @Column(name = "last_updated_by", nullable = false)
    private String lastUpdatedBy;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lec_status", nullable = false)
    private LecStatusEnum lecStatus;

    @Column(name = "jhi_desc")
    private String desc;

    @ManyToOne
    @JsonIgnoreProperties("")
    private AttendanceMaster attendancemaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLecDate() {
        return lecDate;
    }

    public Lecture lecDate(LocalDate lecDate) {
        this.lecDate = lecDate;
        return this;
    }

    public void setLecDate(LocalDate lecDate) {
        this.lecDate = lecDate;
    }

    public LocalDate getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public Lecture lastUpdatedOn(LocalDate lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
        return this;
    }

    public void setLastUpdatedOn(LocalDate lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Lecture lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public LecStatusEnum getLecStatus() {
        return lecStatus;
    }

    public Lecture lecStatus(LecStatusEnum lecStatus) {
        this.lecStatus = lecStatus;
        return this;
    }

    public void setLecStatus(LecStatusEnum lecStatus) {
        this.lecStatus = lecStatus;
    }

    public String getDesc() {
        return desc;
    }

    public Lecture desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public AttendanceMaster getAttendancemaster() {
        return attendancemaster;
    }

    public Lecture attendancemaster(AttendanceMaster attendanceMaster) {
        this.attendancemaster = attendanceMaster;
        return this;
    }

    public void setAttendancemaster(AttendanceMaster attendanceMaster) {
        this.attendancemaster = attendanceMaster;
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
        Lecture lecture = (Lecture) o;
        if (lecture.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lecture.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Lecture{" +
            "id=" + getId() +
            ", lecDate='" + getLecDate() + "'" +
            ", lastUpdatedOn='" + getLastUpdatedOn() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lecStatus='" + getLecStatus() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
