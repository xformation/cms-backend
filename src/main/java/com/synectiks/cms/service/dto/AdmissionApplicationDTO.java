package com.synectiks.cms.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.AdmissionStatusEnum;
import com.synectiks.cms.domain.enumeration.CourseEnum;

/**
 * A DTO for the AdmissionApplication entity.
 */
public class AdmissionApplicationDTO implements Serializable {

    private Long id;

    @NotNull
    private AdmissionStatusEnum admissionStatus;

    @NotNull
    private CourseEnum course;

    @NotNull
    private LocalDate date;

    @NotNull
    private String comments;

    private Long studentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmissionStatusEnum getAdmissionStatus() {
        return admissionStatus;
    }

    public void setAdmissionStatus(AdmissionStatusEnum admissionStatus) {
        this.admissionStatus = admissionStatus;
    }

    public CourseEnum getCourse() {
        return course;
    }

    public void setCourse(CourseEnum course) {
        this.course = course;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdmissionApplicationDTO admissionApplicationDTO = (AdmissionApplicationDTO) o;
        if (admissionApplicationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), admissionApplicationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdmissionApplicationDTO{" +
            "id=" + getId() +
            ", admissionStatus='" + getAdmissionStatus() + "'" +
            ", course='" + getCourse() + "'" +
            ", date='" + getDate() + "'" +
            ", comments='" + getComments() + "'" +
            ", student=" + getStudentId() +
            "}";
    }
}
