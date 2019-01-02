package com.synectiks.cms.graphql.types.Periods;

import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.enumeration.ClassPeriods;

import java.util.Objects;

public class AbstractPeriodsInput {
    private Long id;
    private ClassPeriods periods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassPeriods getPeriods() {
        return periods;
    }

    public void setPeriods(ClassPeriods periods) {
        this.periods = periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPeriodsInput)) return false;
        AbstractPeriodsInput that = (AbstractPeriodsInput) o;
        return Objects.equals(getId(), that.getId()) &&
            getPeriods() == that.getPeriods();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPeriods());
    }

    @Override
    public String toString() {
        return "AbstractPeriodsInput{" +
            "id=" + id +
            ", periods=" + periods +
            '}';
    }
}
