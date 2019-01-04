package com.synectiks.cms.graphql.types.AcademicSubject;

import com.synectiks.cms.domain.AcademicSubject;

public class AddAcademicSubjectPayload extends  AbstractAcademicSubjectPayload {
    public AddAcademicSubjectPayload(AcademicSubject academicSubject) {
        super(academicSubject);
    }
}
