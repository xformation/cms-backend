package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.Status;

/**
 * A StudentAttendance.
 */
@Entity
@Table(name = "student_attendance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "studentattendance")
public class StudentAttendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "attendance_date", nullable = false)
    private Date attendanceDate;

    @NotNull
    @Column(name = "s_name", nullable = false)
    private String sName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @NotNull
    @Column(name = "comments", nullable = false)
    private String comments;

    @ManyToOne
    @JsonIgnoreProperties("")
    private StudentYear studentYear;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Departments departments;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Subject subject;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Semester semester;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Section section;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Periods periods;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Student student;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getsName() {
        return sName;
    }

    public StudentAttendance sName(String sName) {
        this.sName = sName;
        return this;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Status getStatus() {
        return status;
    }

    public StudentAttendance status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public StudentAttendance comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public StudentYear getStudentYear() {
        return studentYear;
    }

    public StudentAttendance studentYear(StudentYear studentYear) {
        this.studentYear = studentYear;
        return this;
    }

    public void setStudentYear(StudentYear studentYear) {
        this.studentYear = studentYear;
    }

    public Departments getDepartments() {
        return departments;
    }

    public StudentAttendance departments(Departments departments) {
        this.departments = departments;
        return this;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    public Subject getSubject() {
        return subject;
    }

    public StudentAttendance subject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Semester getSemester() {
        return semester;
    }

    public StudentAttendance semester(Semester semester) {
        this.semester = semester;
        return this;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Section getSection() {
        return section;
    }

    public StudentAttendance section(Section section) {
        this.section = section;
        return this;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Periods getPeriods() {
        return periods;
    }

    public StudentAttendance periods(Periods periods) {
        this.periods = periods;
        return this;
    }

    public void setPeriods(Periods periods) {
        this.periods = periods;
    }

    public Student getStudent() {
        return student;
    }

    public StudentAttendance student(Student student) {
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
        StudentAttendance studentAttendance = (StudentAttendance) o;
        if (studentAttendance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentAttendance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentAttendance{" +
            "id=" + getId() +
            ", attendanceDate='" + getAttendanceDate() + "'" +
            ", sName='" + getsName() + "'" +
            ", status='" + getStatus() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
