package com.synectiks.cms.graphql.types.AcademicSubject;

import com.synectiks.cms.domain.AcademicSubject;

public class AbstractAcademicSubjectPayload {
    private final AcademicSubject academicSubject;


    public AbstractAcademicSubjectPayload(AcademicSubject academicSubject) {
        this.academicSubject = academicSubject;
    }

    public AcademicSubject getAcademicSubject() {
        return academicSubject;
    }
}
