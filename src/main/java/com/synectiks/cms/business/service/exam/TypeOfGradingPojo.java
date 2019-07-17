package com.synectiks.cms.business.service.exam;

import java.io.Serializable;
import java.util.Objects;

public class TypeOfGradingPojo implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer minMarks;
    private Integer maxMarks;
    private String grades;
    private Long nextId;


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
        TypeOfGradingPojo that = (TypeOfGradingPojo) o;
        return Objects.equals(minMarks, that.minMarks) &&
            Objects.equals(maxMarks, that.maxMarks) &&
            Objects.equals(grades, that.grades) &&
            Objects.equals(nextId, that.nextId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minMarks, maxMarks, grades, nextId);
    }

    @Override
    public String toString() {
        return "TypeOfGradingPojo{" +
            "minMarks=" + minMarks +
            ", maxMarks=" + maxMarks +
            ", grades='" + grades + '\'' +
            ", nextId=" + nextId +
            '}';
    }
}
