package com.synectiks.cms.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CompetitiveExam entity.
 */
public class CompetitiveExamDTO implements Serializable {

    private Long id;

    @NotNull
    private String testName;

    @NotNull
    private Integer testScore;

    @NotNull
    private Long enrollmentNo;

    @NotNull
    private Long rank;


    private Long studentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getTestScore() {
        return testScore;
    }

    public void setTestScore(Integer testScore) {
        this.testScore = testScore;
    }

    public Long getEnrollmentNo() {
        return enrollmentNo;
    }

    public void setEnrollmentNo(Long enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
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

        CompetitiveExamDTO competitiveExamDTO = (CompetitiveExamDTO) o;
        if (competitiveExamDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), competitiveExamDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompetitiveExamDTO{" +
            "id=" + getId() +
            ", testName='" + getTestName() + "'" +
            ", testScore=" + getTestScore() +
            ", enrollmentNo=" + getEnrollmentNo() +
            ", rank=" + getRank() +
            ", student=" + getStudentId() +
            "}";
    }
}
