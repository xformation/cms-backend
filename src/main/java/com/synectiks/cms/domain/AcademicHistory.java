package com.synectiks.cms.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synectiks.cms.utils.IESEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AcademicHistory.
 */
@Entity
@Table(name = "academic_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AcademicHistory implements Serializable, IESEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "qualification", nullable = false)
    private String qualification;

    @NotNull
    @Column(name = "year_of_passing", nullable = false)
    private String yearOfPassing;

    @NotNull
    @Column(name = "institution", nullable = false)
    private String institution;

    @NotNull
    @Column(name = "university", nullable = false)
    private String university;

    @NotNull
    @Column(name = "enrollment_no", nullable = false)
    private Long enrollmentNo;

    @NotNull
    @Column(name = "score", nullable = false)
    private Long score;

    @NotNull
    @Column(name = "percentage", nullable = false)
    private Integer percentage;

    @ManyToOne
    @JsonIgnoreProperties("academicHistories")
    private Student student;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public AcademicHistory qualification(String qualification) {
        this.qualification = qualification;
        return this;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getYearOfPassing() {
        return yearOfPassing;
    }

    public AcademicHistory yearOfPassing(String yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
        return this;
    }

    public void setYearOfPassing(String yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }

    public String getInstitution() {
        return institution;
    }

    public AcademicHistory institution(String institution) {
        this.institution = institution;
        return this;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getUniversity() {
        return university;
    }

    public AcademicHistory university(String university) {
        this.university = university;
        return this;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Long getEnrollmentNo() {
        return enrollmentNo;
    }

    public AcademicHistory enrollmentNo(Long enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
        return this;
    }

    public void setEnrollmentNo(Long enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public Long getScore() {
        return score;
    }

    public AcademicHistory score(Long score) {
        this.score = score;
        return this;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public AcademicHistory percentage(Integer percentage) {
        this.percentage = percentage;
        return this;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Student getStudent() {
        return student;
    }

    public AcademicHistory student(Student student) {
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
        AcademicHistory academicHistory = (AcademicHistory) o;
        if (academicHistory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), academicHistory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AcademicHistory{" +
            "id=" + getId() +
            ", qualification='" + getQualification() + "'" +
            ", yearOfPassing='" + getYearOfPassing() + "'" +
            ", institution='" + getInstitution() + "'" +
            ", university='" + getUniversity() + "'" +
            ", enrollmentNo=" + getEnrollmentNo() +
            ", score=" + getScore() +
            ", percentage=" + getPercentage() +
            "}";
    }
}
