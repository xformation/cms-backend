package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Library entity.
 */
public class LibraryDTO implements Serializable {

    private Long id;

    @NotNull
    private String bookTitle;

    @NotNull
    private String author;

    @NotNull
    private Long noOfCopies;

    @NotNull
    private Long bookId;

    private Long batchId;

    private Long subjectId;

    private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(Long noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LibraryDTO libraryDTO = (LibraryDTO) o;
        if (libraryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), libraryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LibraryDTO{" +
            "id=" + getId() +
            ", bookTitle='" + getBookTitle() + "'" +
            ", author='" + getAuthor() + "'" +
            ", noOfCopies=" + getNoOfCopies() +
            ", bookId=" + getBookId() +
            ", batch=" + getBatchId() +
            ", subject=" + getSubjectId() +
            ", department=" + getDepartmentId() +
            "}";
    }
}
