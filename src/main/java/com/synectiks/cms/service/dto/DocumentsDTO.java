package com.synectiks.cms.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Documents entity.
 */
public class DocumentsDTO implements Serializable {

    private Long id;

    @NotNull
    private String documentName;

    @NotNull
    private String upload;


    private Long studentId;

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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocumentsDTO documentsDTO = (DocumentsDTO) o;
        if (documentsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentsDTO{" +
            "id=" + getId() +
            ", documentName='" + getDocumentName() + "'" +
            ", upload='" + getUpload() + "'" +
            ", student=" + getStudentId() +
            "}";
    }
}
