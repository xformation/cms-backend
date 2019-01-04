package com.synectiks.cms.graphql.types.AcademicSubject;

public class AddAcademicSubjectInput extends AbstractAcademicSubjectInput {

    private Long academicDepartmentId;

    public Long getAcademicDepartmentId() {
        return academicDepartmentId;
    }

    public void setAcademicDepartmentId(Long academicDepartmentId) {
        this.academicDepartmentId = academicDepartmentId;
    }

    @Override
    public String toString() {
        return "AddAcademicSubjectInput{" +
            "academicDepartmentId=" + academicDepartmentId +
            '}' + super.toString();
    }
}
