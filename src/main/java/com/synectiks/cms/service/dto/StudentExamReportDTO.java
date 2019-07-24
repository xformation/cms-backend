package com.synectiks.cms.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the StudentExamReport entity.
 */
public class StudentExamReportDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer marksObtained;

    @NotNull
    private String comments;

    @NotNull
    private LocalDate createdOn;

    @NotNull
    private String createdBy;

    @NotNull
    private LocalDate updatedOn;

    @NotNull
    private String updatedBy;


    private Long academicExamSettingId;

    private Long studentId;

    private Long departmentId;

    private Long typeOfGradingId;

    private Long batchId;

    private Long academicyearId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(Integer marksObtained) {
        this.marksObtained = marksObtained;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getAcademicExamSettingId() {
        return academicExamSettingId;
    }

    public void setAcademicExamSettingId(Long academicExamSettingId) {
        this.academicExamSettingId = academicExamSettingId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getTypeOfGradingId() {
        return typeOfGradingId;
    }

    public void setTypeOfGradingId(Long typeOfGradingId) {
        this.typeOfGradingId = typeOfGradingId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicYearId) {
        this.academicyearId = academicYearId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StudentExamReportDTO studentExamReportDTO = (StudentExamReportDTO) o;
        if (studentExamReportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentExamReportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentExamReportDTO{" +
            "id=" + getId() +
            ", marksObtained=" + getMarksObtained() +
            ", comments='" + getComments() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", academicExamSetting=" + getAcademicExamSettingId() +
            ", student=" + getStudentId() +
            ", department=" + getDepartmentId() +
            ", typeOfGrading=" + getTypeOfGradingId() +
            ", batch=" + getBatchId() +
            ", academicyear=" + getAcademicyearId() +
            "}";
    }
}
