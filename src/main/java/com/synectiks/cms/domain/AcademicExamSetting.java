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

import com.synectiks.cms.domain.enumeration.SemesterEnum;

import com.synectiks.cms.domain.enumeration.GradeType;

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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "semester", nullable = false)
    private SemesterEnum semester;

    @NotNull
    @Column(name = "exam_date", nullable = false)
    private LocalDate examDate;

    @NotNull
    @Column(name = "duration", nullable = false)
    private String duration;

    @NotNull
    @Column(name = "day", nullable = false)
    private String day;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private String startTime;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private String endTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "grade_type", nullable = false)
    private GradeType gradeType;

    @NotNull
    @Column(name = "total", nullable = false)
    private Integer total;

    @NotNull
    @Column(name = "passing", nullable = false)
    private Integer passing;

    @Column(name = "actions")
    private String actions;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JsonIgnoreProperties("academicExamSettings")
    private Branch branch;

    @ManyToOne
    @JsonIgnoreProperties("academicExamSettings")
    private Subject subject;

    @ManyToOne
    @JsonIgnoreProperties("academicExamSettings")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("academicExamSettings")
    private AcademicYear academicyear;

    @ManyToOne
    @JsonIgnoreProperties("academicExamSettings")
    private Section section;

    @ManyToOne
    @JsonIgnoreProperties("academicExamSettings")
    private Batch batch;

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

    public SemesterEnum getSemester() {
        return semester;
    }

    public AcademicExamSetting semester(SemesterEnum semester) {
        this.semester = semester;
        return this;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public AcademicExamSetting examDate(LocalDate examDate) {
        this.examDate = examDate;
        return this;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
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

    public GradeType getGradeType() {
        return gradeType;
    }

    public AcademicExamSetting gradeType(GradeType gradeType) {
        this.gradeType = gradeType;
        return this;
    }

    public void setGradeType(GradeType gradeType) {
        this.gradeType = gradeType;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public AcademicExamSetting startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public AcademicExamSetting endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Branch getBranch() {
        return branch;
    }

    public AcademicExamSetting branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Subject getSubject() {
        return subject;
    }

    public AcademicExamSetting subject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
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

    public AcademicYear getAcademicyear() {
        return academicyear;
    }

    public AcademicExamSetting academicyear(AcademicYear academicYear) {
        this.academicyear = academicYear;
        return this;
    }

    public void setAcademicyear(AcademicYear academicYear) {
        this.academicyear = academicYear;
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

    public Batch getBatch() {
        return batch;
    }

    public AcademicExamSetting batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
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
            ", semester='" + getSemester() + "'" +
            ", examDate='" + getExamDate() + "'" +
            ", duration='" + getDuration() + "'" +
            ", day='" + getDay() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", gradeType='" + getGradeType() + "'" +
            ", total=" + getTotal() +
            ", passing=" + getPassing() +
            ", actions='" + getActions() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
