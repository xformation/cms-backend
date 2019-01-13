package com.synectiks.cms.graphql.types.Holiday;

public class UpdateHolidayInput extends AbstractHolidayInput {
	private Long academicYearId;

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }
}
