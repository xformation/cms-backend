package com.synectiks.cms.graphql.types.Section;

public class AddSectionInput extends AbstractSectionInput {

    private Long studentYearId;

    public Long getStudentYearId() {
        return studentYearId;
    }

    public void setStudentYearId(Long studentYearId) {
        this.studentYearId = studentYearId;
    }

    @Override
    public String toString() {
        return "AddSectionInput{" +
            "studentYearId=" + studentYearId +
            '}'+ super.toString();
    }
}
