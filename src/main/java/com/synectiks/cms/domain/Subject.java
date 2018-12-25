package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.Common;

import com.synectiks.cms.domain.enumeration.Elective;

/**
 * A Subject.
 */
@Entity
@Table(name = "subject")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "subject")
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "common_sub", nullable = false)
    private Common commonSub;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "elective_sub", nullable = false)
    private Elective electiveSub;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Periods periods;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Teacher teacher;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Common getCommonSub() {
        return commonSub;
    }

    public Subject commonSub(Common commonSub) {
        this.commonSub = commonSub;
        return this;
    }

    public void setCommonSub(Common commonSub) {
        this.commonSub = commonSub;
    }

    public Elective getElectiveSub() {
        return electiveSub;
    }

    public Subject electiveSub(Elective electiveSub) {
        this.electiveSub = electiveSub;
        return this;
    }

    public void setElectiveSub(Elective electiveSub) {
        this.electiveSub = electiveSub;
    }

    public Periods getPeriods() {
        return periods;
    }

    public Subject periods(Periods periods) {
        this.periods = periods;
        return this;
    }

    public void setPeriods(Periods periods) {
        this.periods = periods;
    }

    public Student getStudent() {
        return student;
    }

    public Subject student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Subject teacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
        Subject subject = (Subject) o;
        if (subject.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), subject.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Subject{" +
            "id=" + getId() +
            ", commonSub='" + getCommonSub() + "'" +
            ", electiveSub='" + getElectiveSub() + "'" +
            "}";
    }
}
