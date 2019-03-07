package com.synectiks.cms.graphql.types.CompetitiveExam;

public class UpdateCompetitiveExamInput extends AbstractCompetitiveExamInput {
    private Long studentId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
