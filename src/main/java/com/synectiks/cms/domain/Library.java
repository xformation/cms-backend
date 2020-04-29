package com.synectiks.cms.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Library.
 */
@Entity
@Table(name = "library")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Library implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "row_name")
    private String rowName;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "book_no")
    private Long bookNo;

    @Column(name = "author")
    private String author;

    @Column(name = "no_of_copies")
    private Long noOfCopies;

    @Column(name = "unique_no")
    private Long uniqueNo;

    @Column(name = "department_id")
    private Long departmentId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRowName() {
        return rowName;
    }

    public Library rowName(String rowName) {
        this.rowName = rowName;
        return this;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Library bookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Long getBookNo() {
        return bookNo;
    }

    public Library bookNo(Long bookNo) {
        this.bookNo = bookNo;
        return this;
    }

    public void setBookNo(Long bookNo) {
        this.bookNo = bookNo;
    }

    public String getAuthor() {
        return author;
    }

    public Library author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getNoOfCopies() {
        return noOfCopies;
    }

    public Library noOfCopies(Long noOfCopies) {
        this.noOfCopies = noOfCopies;
        return this;
    }

    public void setNoOfCopies(Long noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    public Long getUniqueNo() {
        return uniqueNo;
    }

    public Library uniqueNo(Long uniqueNo) {
        this.uniqueNo = uniqueNo;
        return this;
    }

    public void setUniqueNo(Long uniqueNo) {
        this.uniqueNo = uniqueNo;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public Library departmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Library library = (Library) o;
        if (library.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), library.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Library{" +
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
