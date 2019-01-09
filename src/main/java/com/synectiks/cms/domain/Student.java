package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.Elective;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "student_name", nullable = false)
    private String studentName;

    @NotNull
    @Column(name = "attendance", nullable = false)
    private Boolean attendance;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "elective_sub", nullable = false)
    private Elective electiveSub;

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

    public String getStudentName() {
        return studentName;
    }

    public Student studentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Boolean isAttendance() {
        return attendance;
    }

    public Student attendance(Boolean attendance) {
        this.attendance = attendance;
        return this;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }

    public Elective getElectiveSub() {
        return electiveSub;
    }

    public Student electiveSub(Elective electiveSub) {
        this.electiveSub = electiveSub;
        return this;
    }

    public void setElectiveSub(Elective electiveSub) {
        this.electiveSub = electiveSub;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Student teacher(Teacher teacher) {
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
        Student student = (Student) o;
        if (student.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", studentName='" + getStudentName() + "'" +
            ", attendance='" + isAttendance() + "'" +
            ", electiveSub='" + getElectiveSub() + "'" +
            "}";
    }
}
