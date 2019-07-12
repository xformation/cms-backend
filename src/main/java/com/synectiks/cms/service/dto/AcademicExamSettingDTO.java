package com.synectiks.cms.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.SemesterEnum;
import com.synectiks.cms.domain.enumeration.GradeType;

/**
 * A DTO for the AcademicExamSetting entity.
 */
public class AcademicExamSettingDTO implements Serializable {

    private Long id;

    @NotNull
    private String examName;

    @NotNull
    private SemesterEnum semester;

    @NotNull
    private LocalDate examDate;

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;

    private GradeType gradeType;

    @NotNull
    private Integer total;

    @NotNull
    private Integer passing;

    private String actions;


    private Long branchId;

    private Long subjectId;

    private Long departmentId;

    private Long typeOfGradingId;

    private Long academicyearId;

    private Long sectionId;

    private Long batchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public SemesterEnum getSemester() {
        return semester;
    }

    public void setSemester(SemesterEnum semester) {
        this.semester = semester;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
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

    public GradeType getGradeType() {
        return gradeType;
    }

    public void setGradeType(GradeType gradeType) {
        this.gradeType = gradeType;
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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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

    public Long getTypeOfGradingId() {
        return typeOfGradingId;
    }

    public void setTypeOfGradingId(Long typeOfGradingId) {
        this.typeOfGradingId = typeOfGradingId;
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

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
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
            ", examName='" + getExamName() + "'" +
            ", semester='" + getSemester() + "'" +
            ", examDate='" + getExamDate() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", gradeType='" + getGradeType() + "'" +
            ", total=" + getTotal() +
            ", passing=" + getPassing() +
            ", actions='" + getActions() + "'" +
            ", branch=" + getBranchId() +
            ", subject=" + getSubjectId() +
            ", department=" + getDepartmentId() +
            ", typeOfGrading=" + getTypeOfGradingId() +
            ", academicyear=" + getAcademicyearId() +
            ", section=" + getSectionId() +
            ", batch=" + getBatchId() +
            "}";
    }
}
