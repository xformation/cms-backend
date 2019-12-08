package com.synectiks.cms.graphql.types.AcademicHistory;

import com.synectiks.cms.entities.AcademicHistory;

public class AbstractAcademicHistoryPayload {
    private  final AcademicHistory academicHistory;

    public AbstractAcademicHistoryPayload(AcademicHistory academicHistory) {
        this.academicHistory = academicHistory;
    }

    public AcademicHistory getAcademicHistory() {
        return academicHistory;
    }
}
