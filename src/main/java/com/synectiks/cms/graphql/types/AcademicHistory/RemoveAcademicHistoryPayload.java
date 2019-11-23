package com.synectiks.cms.graphql.types.AcademicHistory;

import com.synectiks.commons.entities.cms.AcademicHistory;

import java.util.List;

public class RemoveAcademicHistoryPayload {
    private final List<AcademicHistory> academicHistories;

    public RemoveAcademicHistoryPayload(List<AcademicHistory> academicHistories){
        this.academicHistories = academicHistories;
    }
    public List<AcademicHistory> getAcademicHistories() {
        return academicHistories;
    }
}
