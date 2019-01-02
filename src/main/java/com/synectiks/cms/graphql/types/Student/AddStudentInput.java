package com.synectiks.cms.graphql.types.Student;

public class AddStudentInput extends AbstractStudentInput {
    private Long teacherId;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "AddStudentInput{" +
            "teacherId=" + teacherId +
            '}' + super.toString();
    }
}
