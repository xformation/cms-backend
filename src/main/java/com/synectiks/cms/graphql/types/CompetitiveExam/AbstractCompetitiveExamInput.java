package com.synectiks.cms.graphql.types.CompetitiveExam;

import java.util.Objects;

public class AbstractCompetitiveExamInput {
    private Long id;
    private String testName;
    private Integer testScore;
    private Long enrollmentNo;
    private Long rank;

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

    @Override
    public String toString() {
        return "AbstractCompetitiveExamInput{" +
            "id=" + id +
            ", testName='" + testName + '\'' +
            ", testScore=" + testScore +
            ", enrollmentNo=" + enrollmentNo +
            ", rank=" + rank +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCompetitiveExamInput that = (AbstractCompetitiveExamInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(testName, that.testName) &&
            Objects.equals(testScore, that.testScore) &&
            Objects.equals(enrollmentNo, that.enrollmentNo) &&
            Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testName, testScore, enrollmentNo, rank);
    }
}
