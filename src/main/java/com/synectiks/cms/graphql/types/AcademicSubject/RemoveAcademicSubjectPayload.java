package com.synectiks.cms.graphql.types.AcademicSubject;

import com.synectiks.cms.domain.AcademicSubject;

import java.util.List;

public class RemoveAcademicSubjectPayload {
    private final List<AcademicSubject> academicSubjects;

    public RemoveAcademicSubjectPayload(List<AcademicSubject> academicSubjects) {
        this.academicSubjects = academicSubjects;
    }

    public List<AcademicSubject> getAcademicSubjects() {
        return academicSubjects;
    }
}
