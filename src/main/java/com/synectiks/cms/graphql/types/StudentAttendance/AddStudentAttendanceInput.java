package com.synectiks.cms.graphql.types.StudentAttendance;

public class AddStudentAttendanceInput extends AbstractStudentAttendanceInput {

    private Long studentId;


    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "AddStudentAttendanceInput{" +
            "studentId=" + studentId +
            '}'+ super.toString();
    }
}
