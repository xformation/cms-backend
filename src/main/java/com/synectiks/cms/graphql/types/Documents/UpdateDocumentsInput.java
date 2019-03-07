package com.synectiks.cms.graphql.types.Documents;

public class UpdateDocumentsInput extends AbstractDocumentsInput {

    private Long studentId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }


}
