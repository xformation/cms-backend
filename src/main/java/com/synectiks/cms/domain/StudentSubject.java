package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A StudentSubject.
 */
@Entity
@Table(name = "student_subject")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "studentsubject")
public class StudentSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "comments")
    private String comments;

    @NotNull
    @Column(name = "lastupdated_date", nullable = false)
    private Date lastupdatedDate;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Subject subject;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public StudentSubject comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getLastupdatedDate() {
        return lastupdatedDate;
    }

    public StudentSubject lastupdatedDate(Date lastupdatedDate) {
        this.lastupdatedDate = lastupdatedDate;
        return this;
    }

    public void setLastupdatedDate(Date lastupdatedDate) {
        this.lastupdatedDate = lastupdatedDate;
    }

    public Student getStudent() {
        return student;
    }

    public StudentSubject student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public StudentSubject subject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
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
        StudentSubject studentSubject = (StudentSubject) o;
        if (studentSubject.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentSubject.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentSubject{" +
            "id=" + getId() +
            ", comments='" + getComments() + "'" +
            ", lastupdatedDate='" + getLastupdatedDate() + "'" +
            "}";
    }
}
