package com.synectiks.cms.graphql.types.TypeOfGrading;

import java.util.Objects;

public class AbstractTypeOfGradingInput {
    private Long id;
    private Integer minMarks;
    private Integer maxMarks;
    private String grades;
    private Long nextId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinMarks() {
        return minMarks;
    }

    public void setMinMarks(Integer minMarks) {
        this.minMarks = minMarks;
    }

    public Integer getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(Integer maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public Long getNextId() {
        return nextId;
    }

    public void setNextId(Long nextId) {
        this.nextId = nextId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTypeOfGradingInput that = (AbstractTypeOfGradingInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(minMarks, that.minMarks) &&
            Objects.equals(maxMarks, that.maxMarks) &&
            Objects.equals(grades, that.grades) &&
            Objects.equals(nextId, that.nextId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, minMarks, maxMarks, grades, nextId);
    }

    @Override
    public String toString() {
        return "AbstractTypeOfGradingInput{" +
            "id=" + id +
            ", minMarks=" + minMarks +
            ", maxMarks=" + maxMarks +
            ", grades='" + grades + '\'' +
            ", nextId=" + nextId +
            '}';
    }
}
