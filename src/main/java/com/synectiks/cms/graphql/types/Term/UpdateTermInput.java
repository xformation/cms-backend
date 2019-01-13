package com.synectiks.cms.graphql.types.Term;

public class UpdateTermInput extends AbstractTermInput {
	private Long academicYearId;

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }
}
