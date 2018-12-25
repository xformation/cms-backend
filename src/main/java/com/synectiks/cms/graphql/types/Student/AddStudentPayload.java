package com.synectiks.cms.graphql.types.Student;

import com.synectiks.cms.domain.Student;

public class AddStudentPayload extends  AbstractStudentPayload {

    public AddStudentPayload(Student student) {
        super(student);
    }
}
