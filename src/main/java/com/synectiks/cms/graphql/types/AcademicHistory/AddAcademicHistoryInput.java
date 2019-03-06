package com.synectiks.cms.graphql.types.AcademicHistory;

public class AddAcademicHistoryInput extends AbstractAcademicHistoryInput {
    private Long StudentId;

    public Long getStudentId() {
        return StudentId;
    }

    public void setStudentId(Long studentId) {
        StudentId = studentId;
    }

    @Override
    public String toString() {
        return "AddAcademicHistoryInput{" +
            super.toString();}
}
