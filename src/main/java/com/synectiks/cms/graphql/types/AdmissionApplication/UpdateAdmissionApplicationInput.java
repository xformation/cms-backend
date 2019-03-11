package com.synectiks.cms.graphql.types.AdmissionApplication;

public class UpdateAdmissionApplicationInput extends AbstractAdmissionApplicationInput {
    private Long studentId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
