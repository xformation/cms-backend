package com.synectiks.cms.graphql.types.StudentSubject;

import com.synectiks.cms.domain.StudentSubject;

public class AddStudentSubjectPayload extends AbstractStudentSubjectPayload{
    public AddStudentSubjectPayload(StudentSubject studentSubject) {
        super(studentSubject);
    }
}
