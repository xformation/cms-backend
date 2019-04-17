package com.synectiks.cms.graphql.types.AdminAttendance;

import java.util.Objects;

public class UpdateAdminAttendanceInput extends AbstractAdminAttendanceInput{
    private Long lectureId;
    private Long branchId;
    private Long collegeId;
    private Long sectionId;
    private Long departmentId;
    private Long academicyearId;
    private Long studentId;

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

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UpdateAdminAttendanceInput that = (UpdateAdminAttendanceInput) o;
        return lectureId.equals(that.lectureId) &&
            branchId.equals(that.branchId) &&
            collegeId.equals(that.collegeId) &&
            sectionId.equals(that.sectionId) &&
            departmentId.equals(that.departmentId) &&
            academicyearId.equals(that.academicyearId) &&
            studentId.equals(that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lectureId, branchId, collegeId, sectionId, departmentId, academicyearId, studentId);
    }

    @Override
    public String toString() {
        return "UpdateAdminAttendanceInput{" +
            "lectureId=" + lectureId +
            ", branchId=" + branchId +
            ", collegeId=" + collegeId +
            ", sectionId=" + sectionId +
            ", departmentId=" + departmentId +
            ", academicyearId=" + academicyearId +
            ", studentId=" + studentId +
            '}';
    }
}
