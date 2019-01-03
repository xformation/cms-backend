package com.synectiks.cms.graphql.types.StudentAttendance;

public class AddStudentAttendanceInput extends AbstractStudentAttendanceInput {
    private Long studentYearId;
    private Long departmentsId;
    private Long subjectId;
    private Long semesterId;
    private Long sectionId;
    private Long periodsId;
    private Long studentId;

    public Long getStudentYearId() {
        return studentYearId;
    }

    public void setStudentYearId(Long studentYearId) {
        this.studentYearId = studentYearId;
    }

    public Long getDepartmentsId() {
        return departmentsId;
    }

    public void setDepartmentsId(Long departmentsId) {
        this.departmentsId = departmentsId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getPeriodsId() {
        return periodsId;
    }

    public void setPeriodsId(Long periodsId) {
        this.periodsId = periodsId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "AddStudentAttendanceInput{" +
            "studentYearId=" + studentYearId +
            ", departmentsId=" + departmentsId +
            ", subjectId=" + subjectId +
            ", semesterId=" + semesterId +
            ", sectionId=" + sectionId +
            ", periodsId=" + periodsId +
            ", studentId=" + studentId +
            '}'+ super.toString();
    }
}
