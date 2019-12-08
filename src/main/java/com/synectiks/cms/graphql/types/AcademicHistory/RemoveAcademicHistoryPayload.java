package com.synectiks.cms.graphql.types.AcademicHistory;

import java.util.List;

import com.synectiks.cms.entities.AcademicHistory;

public class RemoveAcademicHistoryPayload {
    private final List<AcademicHistory> academicHistories;

    public RemoveAcademicHistoryPayload(List<AcademicHistory> academicHistories){
        this.academicHistories = academicHistories;
    }
    public List<AcademicHistory> getAcademicHistories() {
        return academicHistories;
    }
}
