package com.synectiks.cms.graphql.types.AcademicSubject;

public class AddAcademicSubjectInput extends AbstractAcademicSubjectInput {

    private Long departmentsId;

    public Long getDepartmentsId() {
        return departmentsId;
    }

    public void setDepartmentsId(Long departmentsId) {
        this.departmentsId = departmentsId;
    }

    @Override
    public String toString() {
        return "AddAcademicSubjectInput{" +
            "departmentsId=" + departmentsId +
            '}'+ super.toString();
    }
}
