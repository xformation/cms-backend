package com.synectiks.cms.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AdminAttendance entity.
 */
public class AdminAttendanceDTO implements Serializable {

    private Long id;

    private LocalDate updatedOn;

    private String updatedBy;


    private Long lectureId;

    private Long branchId;

    private Long collegeId;

    private Long departmentId;

    private Long academicyearId;

    private Long sectionId;

    private Long studentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdminAttendanceDTO adminAttendanceDTO = (AdminAttendanceDTO) o;
        if (adminAttendanceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adminAttendanceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdminAttendanceDTO{" +
            "id=" + getId() +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", lecture=" + getLectureId() +
            ", branch=" + getBranchId() +
            ", college=" + getCollegeId() +
            ", department=" + getDepartmentId() +
            ", academicyear=" + getAcademicyearId() +
            ", section=" + getSectionId() +
            ", student=" + getStudentId() +
            "}";
    }
}
