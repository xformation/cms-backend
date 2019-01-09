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
 * A AcademicSubject.
 */
@Entity
@Table(name = "academic_subject")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "academicsubject")
public class AcademicSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @NotNull
    @Column(name = "elective_sub", nullable = false)
    private Boolean electiveSub;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Departments department;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public AcademicSubject subjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Boolean isElectiveSub() {
        return electiveSub;
    }

    public AcademicSubject electiveSub(Boolean electiveSub) {
        this.electiveSub = electiveSub;
        return this;
    }

    public void setElectiveSub(Boolean electiveSub) {
        this.electiveSub = electiveSub;
    }

    public Departments getDepartment() {
        return department;
    }

    public AcademicSubject department(Departments departments) {
        this.department = departments;
        return this;
    }

    public void setDepartment(Departments departments) {
        this.department = departments;
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
        AcademicSubject academicSubject = (AcademicSubject) o;
        if (academicSubject.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), academicSubject.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AcademicSubject{" +
            "id=" + getId() +
            ", subjectName='" + getSubjectName() + "'" +
            ", electiveSub='" + isElectiveSub() + "'" +
            "}";
    }
}
