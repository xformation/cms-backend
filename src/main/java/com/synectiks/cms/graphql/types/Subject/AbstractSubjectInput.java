package com.synectiks.cms.graphql.types.Subject;

import com.synectiks.cms.domain.Periods;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.enumeration.Common;
import com.synectiks.cms.domain.enumeration.Elective;

import java.util.Objects;

public class AbstractSubjectInput {
    private Long id;
    private Common commonSub;
    private Elective electiveSub;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Common getCommonSub() {
        return commonSub;
    }

    public void setCommonSub(Common commonSub) {
        this.commonSub = commonSub;
    }

    public Elective getElectiveSub() {
        return electiveSub;
    }

    public void setElectiveSub(Elective electiveSub) {
        this.electiveSub = electiveSub;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractSubjectInput)) return false;
        AbstractSubjectInput that = (AbstractSubjectInput) o;
        return Objects.equals(getId(), that.getId()) &&
            getCommonSub() == that.getCommonSub() &&
            getElectiveSub() == that.getElectiveSub();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCommonSub(), getElectiveSub());
    }

    @Override
    public String toString() {
        return "AbstractSubjectInput{" +
            "id=" + id +
            ", commonSub=" + commonSub +
            ", electiveSub=" + electiveSub +
            '}';
    }
}
