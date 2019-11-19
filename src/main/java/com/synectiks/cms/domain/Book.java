package com.synectiks.cms.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

//import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.StatusEnum;
import com.synectiks.cms.utils.IESEntity;

/**
 * A Book.
 */
@Entity
@Table(name = "book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@Document(indexName = "book")
public class Book implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "issue_date")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate issueDate;

    @Column(name = "due_date")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate dueDate;

    @Column(name = "no_of_copies_available")
    private Integer noOfCopiesAvailable;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "received_date")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate receivedDate;

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

    public StatusEnum getStatus() {
        return status;
    }

    public Book status(StatusEnum status) {
        this.status = status;
        return this;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
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
            ", status='" + getStatus() + "'" +
            ", receivedDate='" + getReceivedDate() + "'" +
            "}";
    }
}
