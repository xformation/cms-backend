package com.synectiks.cms.graphql.types.StudentYear;

import com.synectiks.cms.domain.enumeration.SYear;

import java.util.Objects;

public class AbstractStudentYearInput {
    private Long id;
    private SYear sYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SYear getsYear() {
        return sYear;
    }

    public void setsYear(SYear sYear) {
        this.sYear = sYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractStudentYearInput)) return false;
        AbstractStudentYearInput that = (AbstractStudentYearInput) o;
        return Objects.equals(getId(), that.getId()) &&
            getsYear() == that.getsYear();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getsYear());
    }

    @Override
    public String toString() {
        return "AbstractStudentYearInput{" +
            "id=" + id +
            ", sYear=" + sYear +
            '}';
    }
}
