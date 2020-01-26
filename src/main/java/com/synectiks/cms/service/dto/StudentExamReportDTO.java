package com.synectiks.cms.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.cms.domain.StudentExamReport} entity.
 */
public class StudentExamReportDTO implements Serializable {

    private Long id;

    private Integer marksObtained;

    private String comments;

    private Integer gOp;

    private Long sectionId;

    private Long subjectId;

    private Long departmentId;

    private Long batchId;

    private Long academicyearId;


    private Long academicExamSettingId;

    private Long studentId;

    private Long typeOfGradingId;

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

    public Integer getgOp() {
        return gOp;
    }

    public void setgOp(Integer gOp) {
        this.gOp = gOp;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    public void setAcademicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
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

    public Long getTypeOfGradingId() {
        return typeOfGradingId;
    }

    public void setTypeOfGradingId(Long typeOfGradingId) {
        this.typeOfGradingId = typeOfGradingId;
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
            ", gOp=" + getgOp() +
            ", sectionId=" + getSectionId() +
            ", subjectId=" + getSubjectId() +
            ", departmentId=" + getDepartmentId() +
            ", batchId=" + getBatchId() +
            ", academicyearId=" + getAcademicyearId() +
            ", academicExamSetting=" + getAcademicExamSettingId() +
            ", student=" + getStudentId() +
            ", typeOfGrading=" + getTypeOfGradingId() +
            "}";
    }
}
