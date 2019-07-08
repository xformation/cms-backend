package com.synectiks.cms.graphql.types.Documents;

import java.util.Objects;

public class AbstractDocumentsInput {
    private Long id;
    private String documentName;
    private String documentFilePath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentFilePath() {
        return documentFilePath;
    }

    public void setDocumentFilePath(String documentFilePath) {
        this.documentFilePath = documentFilePath;
    }

    @Override
    public String toString() {
        return "AbstractDocumentsInput{" +
            "id=" + id +
            ", documentName='" + documentName + '\'' +
            ", documentFilePath='" + documentFilePath + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractDocumentsInput)) return false;
        AbstractDocumentsInput that = (AbstractDocumentsInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getDocumentName(), that.getDocumentName()) &&
            Objects.equals(getDocumentFilePath(), that.getDocumentFilePath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDocumentName(), getDocumentFilePath());
    }
}
