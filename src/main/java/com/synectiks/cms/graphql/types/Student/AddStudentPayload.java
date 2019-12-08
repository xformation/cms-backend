package com.synectiks.cms.graphql.types.Student;

import com.synectiks.cms.entities.Student;

public class AddStudentPayload extends  AbstractStudentPayload {

    public AddStudentPayload(Student student) {
        super(student);
    }
}
