package com.synectiks.cms.graphql.types.StudentSubject;

import com.synectiks.cms.domain.StudentSubject;

import java.util.List;

public class RemoveStudentSubjectPayload {
    private final List<StudentSubject> studentSubjects;

    public RemoveStudentSubjectPayload(List<StudentSubject> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }

    public List<StudentSubject> getStudentSubjects() {
        return studentSubjects;
    }
}
