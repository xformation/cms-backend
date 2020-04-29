package com.synectiks.cms.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Library entity.
 */
public class LibraryDTO implements Serializable {

    private Long id;

    private String rowName;

    private String bookTitle;

    private Long bookNo;

    private String author;

    private Long noOfCopies;

    private Long uniqueNo;

    private Long departmentId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Long getBookNo() {
        return bookNo;
    }

    public void setBookNo(Long bookNo) {
        this.bookNo = bookNo;
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

    public Long getUniqueNo() {
        return uniqueNo;
    }

    public void setUniqueNo(Long uniqueNo) {
        this.uniqueNo = uniqueNo;
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
            ", rowName='" + getRowName() + "'" +
            ", bookTitle='" + getBookTitle() + "'" +
            ", bookNo=" + getBookNo() +
            ", author='" + getAuthor() + "'" +
            ", noOfCopies=" + getNoOfCopies() +
            ", uniqueNo=" + getUniqueNo() +
            ", departmentId=" + getDepartmentId() +
            "}";
    }
}
