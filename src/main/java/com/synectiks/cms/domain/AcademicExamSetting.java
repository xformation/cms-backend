package com.synectiks.cms.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * A AcademicExamSetting.
 */
@Entity
@Table(name = "academic_exam_setting")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "academicexamsetting")
public class AcademicExamSetting implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "exam_type", nullable = false)
    private String examType;

    @Column(name = "subject")
    private String subject;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private Date date;

    @NotNull
    @Column(name = "day", nullable = false)
    private String day;

    @NotNull
    @Column(name = "duration", nullable = false)
    private String duration;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private String startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private String endTime;

    @NotNull
    @Column(name = "total", nullable = false)
    private Integer total;

    @NotNull
    @Column(name = "passing", nullable = false)
    private Integer passing;

    @NotNull
    @Column(name = "actions", nullable = false)
    private String actions;

    @ManyToOne
    @JsonIgnoreProperties("academicExamSettings")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("academicExamSettings")
    private AcademicYear academicYear;

    @ManyToOne
    @JsonIgnoreProperties("academicExamSettings")
    private AttendanceMaster attendanceMaster;

    @ManyToOne
    @JsonIgnoreProperties("academicExamSettings")
    private Section section;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamType() {
        return examType;
    }

    public AcademicExamSetting examType(String examType) {
        this.examType = examType;
        return this;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getSubject() {
        return subject;
    }

    public AcademicExamSetting subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public AcademicExamSetting date(Date date) {
        this.date = date;
        return this;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public AcademicExamSetting day(String day) {
        this.day = day;
        return this;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDuration() {
        return duration;
    }

    public AcademicExamSetting duration(String duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public AcademicExamSetting startTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public AcademicExamSetting endTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getTotal() {
        return total;
    }

    public AcademicExamSetting total(Integer total) {
        this.total = total;
        return this;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPassing() {
        return passing;
    }

    public AcademicExamSetting passing(Integer passing) {
        this.passing = passing;
        return this;
    }

    public void setPassing(Integer passing) {
        this.passing = passing;
    }

    public String getActions() {
        return actions;
    }

    public AcademicExamSetting actions(String actions) {
        this.actions = actions;
        return this;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public Department getDepartment() {
        return department;
    }

    public AcademicExamSetting department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public AcademicExamSetting academicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
        return this;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public AttendanceMaster getAttendanceMaster() {
        return attendanceMaster;
    }

    public AcademicExamSetting attendanceMaster(AttendanceMaster attendanceMaster) {
        this.attendanceMaster = attendanceMaster;
        return this;
    }

    public void setAttendanceMaster(AttendanceMaster attendanceMaster) {
        this.attendanceMaster = attendanceMaster;
    }

    public Section getSection() {
        return section;
    }

    public AcademicExamSetting section(Section section) {
        this.section = section;
        return this;
    }

    public void setSection(Section section) {
        this.section = section;
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
        AcademicExamSetting academicExamSetting = (AcademicExamSetting) o;
        if (academicExamSetting.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), academicExamSetting.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AcademicExamSetting{" +
            "id=" + getId() +
            ", examType='" + getExamType() + "'" +
            ", subject='" + getSubject() + "'" +
            ", date='" + getDate() + "'" +
            ", day='" + getDay() + "'" +
            ", duration='" + getDuration() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", total=" + getTotal() +
            ", passing=" + getPassing() +
            ", actions='" + getActions() + "'" +
            "}";
    }
}
