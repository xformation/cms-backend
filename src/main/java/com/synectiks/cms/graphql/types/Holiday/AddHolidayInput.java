package com.synectiks.cms.graphql.types.Holiday;

public class AddHolidayInput extends AbstractHolidayInput {
    private Long academicYearId;

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    @Override
    public String toString() {
        return "AddHolidayInput{" +
            "academicYearId=" + academicYearId +
            '}'+ super.toString();
    }
}
