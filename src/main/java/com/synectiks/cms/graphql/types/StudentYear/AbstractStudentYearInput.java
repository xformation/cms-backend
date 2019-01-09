package com.synectiks.cms.graphql.types.StudentYear;

import com.synectiks.cms.domain.enumeration.SYear;

import java.util.Objects;

public class AbstractStudentYearInput {
    private Long id;
    private SYear yearDes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SYear getYearDes() {
        return yearDes;
    }

    public void setYearDes(SYear yearDes) {
        this.yearDes = yearDes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractStudentYearInput that = (AbstractStudentYearInput) o;
        return Objects.equals(id, that.id) &&
            yearDes == that.yearDes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, yearDes);
    }

    @Override
    public String toString() {
        return "AbstractStudentYearInput{" +
            "id=" + id +
            ", yearDes=" + yearDes +
            '}';
    }
}
