package com.synectiks.cms.graphql.types.AcademicYear;

    import java.util.List;

import com.synectiks.cms.entities.AcademicYear;

public class RemoveAcademicYearPayload {
    private final List<AcademicYear> academicYears;

    public RemoveAcademicYearPayload(List<AcademicYear> academicYears){
        this.academicYears = academicYears;
    }

    public List<AcademicYear> getAcademicYears() {
        return academicYears;
    }
}
