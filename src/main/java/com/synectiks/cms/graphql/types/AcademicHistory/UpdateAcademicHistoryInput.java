package com.synectiks.cms.graphql.types.AcademicHistory;

public class UpdateAcademicHistoryInput extends AbstractAcademicHistoryInput {
    private Long StudentId;

    public Long getStudentId() {
        return StudentId;
    }

    public void setStudentId(Long studentId) {
        StudentId = studentId;
    }
}
