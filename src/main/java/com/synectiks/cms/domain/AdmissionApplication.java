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

import com.synectiks.cms.domain.enumeration.AdmissionStatusEnum;

import com.synectiks.cms.domain.enumeration.CourseEnum;

/**
 * A AdmissionApplication.
 */
@Entity
@Table(name = "admission_application")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "admissionapplication")
public class AdmissionApplication implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "admission_status", nullable = false)
    private AdmissionStatusEnum admissionStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "course", nullable = false)
    private CourseEnum course;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private Date date;

    @NotNull
    @Column(name = "comments", nullable = false)
    private String comments;

    @ManyToOne
    @JsonIgnoreProperties("admissionApplications")
    private Student student;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmissionStatusEnum getAdmissionStatus() {
        return admissionStatus;
    }

    public AdmissionApplication admissionStatus(AdmissionStatusEnum admissionStatus) {
        this.admissionStatus = admissionStatus;
        return this;
    }

    public void setAdmissionStatus(AdmissionStatusEnum admissionStatus) {
        this.admissionStatus = admissionStatus;
    }

    public CourseEnum getCourse() {
        return course;
    }

    public AdmissionApplication course(CourseEnum course) {
        this.course = course;
        return this;
    }

    public void setCourse(CourseEnum course) {
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public AdmissionApplication date(Date date) {
        this.date = date;
        return this;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public AdmissionApplication comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Student getStudent() {
        return student;
    }

    public AdmissionApplication student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
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
        AdmissionApplication admissionApplication = (AdmissionApplication) o;
        if (admissionApplication.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), admissionApplication.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdmissionApplication{" +
            "id=" + getId() +
            ", admissionStatus='" + getAdmissionStatus() + "'" +
            ", course='" + getCourse() + "'" +
            ", date='" + getDate() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
