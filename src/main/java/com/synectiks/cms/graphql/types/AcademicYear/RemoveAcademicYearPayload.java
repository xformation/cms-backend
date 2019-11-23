package com.synectiks.cms.graphql.types.AcademicYear;

    import com.synectiks.commons.entities.cms.AcademicYear;

    import java.util.List;

public class RemoveAcademicYearPayload {
    private final List<AcademicYear> academicYears;

    public RemoveAcademicYearPayload(List<AcademicYear> academicYears){
        this.academicYears = academicYears;
    }

    public List<AcademicYear> getAcademicYears() {
        return academicYears;
    }
}
