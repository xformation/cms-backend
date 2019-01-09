package com.synectiks.cms.graphql.types.Term;

public class AddTermInput extends AbstractTermInput {
    private Long academicYearId;

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    @Override
    public String toString() {
        return "AddTermInput{" +
            "academicYearId=" + academicYearId +
            '}'+super.toString();
    }
}
