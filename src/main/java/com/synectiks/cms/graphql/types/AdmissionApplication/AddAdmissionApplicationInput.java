package com.synectiks.cms.graphql.types.AdmissionApplication;

public class AddAdmissionApplicationInput extends AbstractAdmissionApplicationInput{
    private Long studentId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "AddAdmissionApplicationInput{" +
            "studentId=" + studentId +
            '}';
    }
}
