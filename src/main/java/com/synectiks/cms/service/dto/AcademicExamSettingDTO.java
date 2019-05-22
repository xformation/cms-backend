package com.synectiks.cms.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.SemesterEnum;

/**
 * A DTO for the AcademicExamSetting entity.
 */
public class AcademicExamSettingDTO implements Serializable {

    private Long id;

    @NotNull
    private String examType;

    @NotNull
    private SemesterEnum semester;

    private String subject;

    @NotNull
    private LocalDate examDate;

    @NotNull
    private String day;

    @NotNull
    private String duration;

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;

    @NotNull
    private Integer total;

    @NotNull
    private Integer passing;

    private String actions;

    private Long departmentId;

    private Long academicyearId;

    private Long sectionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public SemesterEnum getSemester() {
        return semester;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPassing() {
        return passing;
    }

    public void setPassing(Integer passing) {
        this.passing = passing;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicYearId) {
        this.academicyearId = academicYearId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AcademicExamSettingDTO academicExamSettingDTO = (AcademicExamSettingDTO) o;
        if (academicExamSettingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), academicExamSettingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AcademicExamSettingDTO{" +
            "id=" + getId() +
            ", examType='" + getExamType() + "'" +
            ", semester='" + getSemester() + "'" +
            ", subject='" + getSubject() + "'" +
            ", examDate='" + getExamDate() + "'" +
            ", day='" + getDay() + "'" +
            ", duration='" + getDuration() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", total=" + getTotal() +
            ", passing=" + getPassing() +
            ", actions='" + getActions() + "'" +
            ", department=" + getDepartmentId() +
            ", academicyear=" + getAcademicyearId() +
            ", section=" + getSectionId() +
            "}";
    }
}
