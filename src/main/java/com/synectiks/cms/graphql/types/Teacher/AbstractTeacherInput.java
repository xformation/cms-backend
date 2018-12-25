package com.synectiks.cms.graphql.types.Teacher;

import com.synectiks.cms.domain.Periods;

import java.util.Objects;

public class AbstractTeacherInput {
    private Long id;
    private String tName;
    private Periods periods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gettName() {
        return tName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractTeacherInput)) return false;
        AbstractTeacherInput that = (AbstractTeacherInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(gettName(), that.gettName()) &&
            Objects.equals(getPeriods(), that.getPeriods());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), gettName(), getPeriods());
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Periods getPeriods() {
        return periods;
    }

    public void setPeriods(Periods periods) {
        this.periods = periods;
    }

    @Override
    public String toString() {
        return "AbstractTeacherInput{" +
            "id=" + id +
            ", tName='" + tName + '\'' +
            ", periods=" + periods +
            '}';
    }
}
