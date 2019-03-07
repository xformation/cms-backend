package com.synectiks.cms.graphql.types.Documents;

import java.util.Objects;

public class AbstractDocumentsInput {
    private Long id;
    private String documentName;
    private String upload;

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

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    @Override
    public String toString() {
        return "AbstractDocumentsInput{" +
            "id=" + id +
            ", documentName='" + documentName + '\'' +
            ", upload='" + upload + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDocumentsInput that = (AbstractDocumentsInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(documentName, that.documentName) &&
            Objects.equals(upload, that.upload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentName, upload);
    }
}
