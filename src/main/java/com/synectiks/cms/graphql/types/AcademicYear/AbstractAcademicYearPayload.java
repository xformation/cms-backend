package com.synectiks.cms.graphql.types.AcademicYear;

import com.synectiks.cms.domain.AcademicYear;

public class AbstractAcademicYearPayload {
    private final AcademicYear academicYear;

    public AbstractAcademicYearPayload(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }
}
