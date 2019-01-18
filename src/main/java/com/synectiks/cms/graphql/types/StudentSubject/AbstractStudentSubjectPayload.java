package com.synectiks.cms.graphql.types.StudentSubject;

import com.synectiks.cms.domain.StudentSubject;

public class AbstractStudentSubjectPayload {
    private final StudentSubject studentSubject;

    public AbstractStudentSubjectPayload(StudentSubject studentSubject) {
        this.studentSubject = studentSubject;
    }

    public StudentSubject getStudentSubject() {
        return studentSubject;
    }
}
