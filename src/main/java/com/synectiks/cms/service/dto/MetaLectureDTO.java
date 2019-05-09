package com.synectiks.cms.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MetaLecture entity.
 */
public class MetaLectureDTO implements Serializable {

    private Long id;

    private String weekDay;

    private String startTime;

    private String endTime;

    private Long branchId;

    private Long departmentId;

    private Long subjectId;

    private Long teacherId;

    private Long termId;

    private Long academicyearId;

    private Long sectionId;

    private Long batchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
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

        MetaLectureDTO metaLectureDTO = (MetaLectureDTO) o;
        if (metaLectureDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), metaLectureDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MetaLectureDTO{" +
            "id=" + getId() +
            ", weekDay='" + getWeekDay() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", branch=" + getBranchId() +
            ", department=" + getDepartmentId() +
            ", subject=" + getSubjectId() +
            ", teacher=" + getTeacherId() +
            ", term=" + getTermId() +
            ", academicyear=" + getAcademicyearId() +
            ", section=" + getSectionId() +
            ", batch=" + getBatchId() +
            "}";
    }
}
