package com.synectiks.cms.graphql.types.Department;

public class AddDepartmentInput extends AbstractDepartmentInput {
    private Long studentId;
    private Long collegeId;
    private Long academicYearId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    @Override
    public String toString() {
        return "AddDepartmentInput{" +
            "studentId=" + studentId +
            ", collegeId=" + collegeId +
            ", academicYearId=" + academicYearId +
            '}';
    }
}
