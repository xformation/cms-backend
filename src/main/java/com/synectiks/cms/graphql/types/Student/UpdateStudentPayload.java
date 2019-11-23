package com.synectiks.cms.graphql.types.Student;


import com.synectiks.commons.entities.cms.Student;

public class UpdateStudentPayload extends AbstractStudentPayload {
    public UpdateStudentPayload(Student student) {
        super(student);
    }
}
