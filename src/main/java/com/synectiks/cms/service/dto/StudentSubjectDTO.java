package com.synectiks.cms.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the StudentSubject entity.
 */
public class StudentSubjectDTO implements Serializable {

    private Long id;

    private String comments;

    @NotNull
    private LocalDate lastupdatedDate;

    private Long studentId;

    private Long subjectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDate getLastupdatedDate() {
        return lastupdatedDate;
    }

    public void setLastupdatedDate(LocalDate lastupdatedDate) {
        this.lastupdatedDate = lastupdatedDate;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StudentSubjectDTO studentSubjectDTO = (StudentSubjectDTO) o;
        if (studentSubjectDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentSubjectDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentSubjectDTO{" +
            "id=" + getId() +
            ", comments='" + getComments() + "'" +
            ", lastupdatedDate='" + getLastupdatedDate() + "'" +
            ", student=" + getStudentId() +
            ", subject=" + getSubjectId() +
            "}";
    }
}
