package com.synectiks.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.AttendanceStatusEnum;

/**
 * A AdminAttendance.
 */
@Entity
@Table(name = "admin_attendance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "adminattendance")
public class AdminAttendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "attendance_status", nullable = false)
    private AttendanceStatusEnum attendanceStatus;

    @NotNull
    @Column(name = "comments", nullable = false)
    private String comments;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Student student;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Lecture lecture;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AttendanceStatusEnum getAttendanceStatus() {
        return attendanceStatus;
    }

    public AdminAttendance attendanceStatus(AttendanceStatusEnum attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
        return this;
    }

    public void setAttendanceStatus(AttendanceStatusEnum attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getComments() {
        return comments;
    }

    public AdminAttendance comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Student getStudent() {
        return student;
    }

    public AdminAttendance student(Student student) {
        this.student = student;
        return this;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public AdminAttendance lecture(Lecture lecture) {
        this.lecture = lecture;
        return this;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
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
        AdminAttendance adminAttendance = (AdminAttendance) o;
        if (adminAttendance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adminAttendance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdminAttendance{" +
            "id=" + getId() +
            ", attendanceStatus='" + getAttendanceStatus() + "'" +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
