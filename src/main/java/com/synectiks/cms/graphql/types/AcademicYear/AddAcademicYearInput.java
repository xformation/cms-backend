package com.synectiks.cms.graphql.types.AcademicYear;

public class AddAcademicYearInput extends AbstractAcademicYearInput {
    private Long holidayId;
    private Long termId;

    public Long getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Long holidayId) {
        this.holidayId = holidayId;
    }

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    @Override
    public String toString() {
        return "AddAcademicYearInput{" +
            "holidayId=" + holidayId +
            ", termId=" + termId +
            '}'+ super.toString();
    }
}
