package com.synectiks.cms.graphql.types.Student;

public class UpdateStudentInput extends AbstractStudentInput{
    private int studentId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
