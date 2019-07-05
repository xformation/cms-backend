package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AddNewBook.
 */
@Entity
@Table(name = "add_new_book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "addnewbook")
public class AddNewBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "book_title", nullable = false)
    private String bookTitle;

    @NotNull
    @Column(name = "author", nullable = false)
    private String author;

    @NotNull
    @Column(name = "no_of_copies", nullable = false)
    private Long noOfCopies;

    @NotNull
    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Batch batch;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Subject subject;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Department department;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public AddNewBook bookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public AddNewBook author(String author) {
        this.author = author;
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getNoOfCopies() {
        return noOfCopies;
    }

    public AddNewBook noOfCopies(Long noOfCopies) {
        this.noOfCopies = noOfCopies;
        return this;
    }

    public void setNoOfCopies(Long noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    public Long getBookId() {
        return bookId;
    }

    public AddNewBook bookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Batch getBatch() {
        return batch;
    }

    public AddNewBook batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Subject getSubject() {
        return subject;
    }

    public AddNewBook subject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Department getDepartment() {
        return department;
    }

    public AddNewBook department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
        AddNewBook addNewBook = (AddNewBook) o;
        if (addNewBook.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), addNewBook.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AddNewBook{" +
            "id=" + getId() +
            ", bookTitle='" + getBookTitle() + "'" +
            ", author='" + getAuthor() + "'" +
            ", noOfCopies=" + getNoOfCopies() +
            ", bookId=" + getBookId() +
            "}";
    }
}
