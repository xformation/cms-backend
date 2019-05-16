package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AcademicHistory entity.
 */
public class AcademicHistoryDTO implements Serializable {

    private Long id;

    @NotNull
    private String qualification;

    @NotNull
    private String yearOfPassing;

    @NotNull
    private String institution;

    @NotNull
    private String university;

    @NotNull
    private Long enrollmentNo;

    @NotNull
    private Long score;

    @NotNull
    private Integer percentage;

    private Long studentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(String yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Long getEnrollmentNo() {
        return enrollmentNo;
    }

    public void setEnrollmentNo(Long enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
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

        AcademicHistoryDTO academicHistoryDTO = (AcademicHistoryDTO) o;
        if (academicHistoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), academicHistoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AcademicHistoryDTO{" +
            "id=" + getId() +
            ", qualification='" + getQualification() + "'" +
            ", yearOfPassing='" + getYearOfPassing() + "'" +
            ", institution='" + getInstitution() + "'" +
            ", university='" + getUniversity() + "'" +
            ", enrollmentNo=" + getEnrollmentNo() +
            ", score=" + getScore() +
            ", percentage=" + getPercentage() +
            ", student=" + getStudentId() +
            "}";
    }
}
