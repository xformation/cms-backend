package com.synectiks.cms.graphql.types.CompetitiveExam;

public class AddCompetitiveExamInput extends AbstractCompetitiveExamInput{
    private Long studentId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "AddCompetitiveExamInput{" +
            "studentId=" + studentId +
            '}';
    }
}
