package com.synectiks.cms.graphql.types.AdmissionApplication;

public class UpdateAdmissionApplicationInput extends AbstractAdmissionApplicationInput {
    private Long studentId;

    private Long academicyearId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
    }
}
