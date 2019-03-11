package com.synectiks.cms.graphql.types.AcademicHistory;

import java.util.Objects;

public class AbstractAcademicHistoryInput {
    private Long id;
    private String qualification;
    private String yearOfPassing;
    private String  institution;
    private String university;
    private Long enrollmentNo;
    private Long score;
    private Integer percentage;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAcademicHistoryInput that = (AbstractAcademicHistoryInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(qualification, that.qualification) &&
            Objects.equals(yearOfPassing, that.yearOfPassing) &&
            Objects.equals(institution, that.institution) &&
            Objects.equals(university, that.university) &&
            Objects.equals(enrollmentNo, that.enrollmentNo) &&
            Objects.equals(score, that.score) &&
            Objects.equals(percentage, that.percentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qualification, yearOfPassing, institution, university, enrollmentNo, score, percentage);
    }

    @Override
    public String toString() {
        return "AbstractAcademicHistoryInput{" +
            "id=" + id +
            ", qualification='" + qualification + '\'' +
            ", yearOfPassing='" + yearOfPassing + '\'' +
            ", institution='" + institution + '\'' +
            ", university='" + university + '\'' +
            ", enrollmentNo=" + enrollmentNo +
            ", score=" + score +
            ", percentage=" + percentage +
            '}';
    }
}
