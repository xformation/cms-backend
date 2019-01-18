package com.synectiks.cms.graphql.types.StudentSubject;

import com.synectiks.cms.domain.StudentSubject;

public class UpdateStudentSubjectPayload extends AbstractStudentSubjectPayload{
    public UpdateStudentSubjectPayload(StudentSubject studentSubject) {
        super(studentSubject);
    }
}
