package com.synectiks.cms.graphql.types.Student;

import com.synectiks.cms.entities.Student;

public class AbstractStudentPayload {
    private final Student student;

    public AbstractStudentPayload(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}
