package com.synectiks.cms.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Book.
 */
@Entity
@Table(name = "book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "no_of_copies_available")
    private Integer noOfCopiesAvailable;

    @Column(name = "book_status")
    private String bookStatus;

    @Column(name = "received_date")
    private LocalDate receivedDate;

    @Column(name = "batch_id")
    private Long batchId;

    @Column(name = "department_id")
    private Long departmentId;

    @ManyToOne
    @JsonIgnoreProperties("books")
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("books")
    private Library library;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public Book issueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Book dueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getNoOfCopiesAvailable() {
        return noOfCopiesAvailable;
    }

    public Book noOfCopiesAvailable(Integer noOfCopiesAvailable) {
        this.noOfCopiesAvailable = noOfCopiesAvailable;
        return this;
    }

    public void setNoOfCopiesAvailable(Integer noOfCopiesAvailable) {
        this.noOfCopiesAvailable = noOfCopiesAvailable;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public Book bookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
        return this;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public Book receivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Long getBatchId() {
        return batchId;
    }

    public Book batchId(Long batchId) {
        this.batchId = batchId;
        return this;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public Book departmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Student getStudent() {
        return student;
    }

    public Book student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Library getLibrary() {
        return library;
    }

    public Book library(Library library) {
        this.library = library;
        return this;
    }

    public void setLibrary(Library library) {
        this.library = library;
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
        Book book = (Book) o;
        if (book.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + getId() +
            ", issueDate='" + getIssueDate() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", noOfCopiesAvailable=" + getNoOfCopiesAvailable() +
            ", bookStatus='" + getBookStatus() + "'" +
            ", receivedDate='" + getReceivedDate() + "'" +
            ", batchId=" + getBatchId() +
            ", departmentId=" + getDepartmentId() +
            "}";
    }
}
