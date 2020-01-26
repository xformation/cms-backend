package com.synectiks.cms.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.SemesterEnum;
import com.synectiks.cms.domain.enumeration.GradeType;

/**
 * A DTO for the {@link com.synectiks.cms.domain.AcademicExamSetting} entity.
 */
public class AcademicExamSettingDTO implements Serializable {

    private Long id;

    private String examName;

    private SemesterEnum semester;

    private LocalDate examDate;

    private String startTime;

    private String endTime;

    private GradeType gradeType;

    private Integer total;

    private Integer passing;

    private String actions;

    private Long groupvalue;

    private Long countvalue;

    private Long branchId;

    private Long subjectId;

    private Long departmentId;

    private Long academicyearId;

    private Long sectionId;

    private Long batchId;


    private Long typeOfGradingId;

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

    public Long getGroupvalue() {
        return groupvalue;
    }

    public void setGroupvalue(Long groupvalue) {
        this.groupvalue = groupvalue;
    }

    public Long getCountvalue() {
        return countvalue;
    }

    public void setCountvalue(Long countvalue) {
        this.countvalue = countvalue;
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

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
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
            ", groupvalue=" + getGroupvalue() +
            ", countvalue=" + getCountvalue() +
            ", branchId=" + getBranchId() +
            ", subjectId=" + getSubjectId() +
            ", departmentId=" + getDepartmentId() +
            ", academicyearId=" + getAcademicyearId() +
            ", sectionId=" + getSectionId() +
            ", batchId=" + getBatchId() +
            ", typeOfGrading=" + getTypeOfGradingId() +
            "}";
    }
}
