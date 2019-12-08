package com.synectiks.cms.graphql.types.Student;


import com.synectiks.cms.entities.Student;

public class UpdateStudentPayload extends AbstractStudentPayload {
    public UpdateStudentPayload(Student student) {
        super(student);
    }
}
