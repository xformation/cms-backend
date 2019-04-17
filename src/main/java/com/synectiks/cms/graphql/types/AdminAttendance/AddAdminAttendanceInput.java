package com.synectiks.cms.graphql.types.AdminAttendance;

public class AddAdminAttendanceInput extends AbstractAdminAttendanceInput{
    private Long lectureId;
    private Long branchId;
    private Long collegeId;
    private Long departmentId;
    private Long academicyearId;
    private Long sectionId;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "AddAdminAttendanceInput{" +
            "lectureId=" + lectureId +
            ", branchId=" + branchId +
            ", collegeId=" + collegeId +
            ", departmentId=" + departmentId +
            ", academicyearId=" + academicyearId +
            ", sectionId=" + sectionId +
            ", studentId=" + studentId +
            '}';
    }
}
