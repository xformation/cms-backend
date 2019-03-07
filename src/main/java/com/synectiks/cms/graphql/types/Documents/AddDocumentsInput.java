package com.synectiks.cms.graphql.types.Documents;

public class AddDocumentsInput extends AbstractDocumentsInput {

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
