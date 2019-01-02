package com.synectiks.cms.graphql.types.Section;

import com.synectiks.cms.domain.StudentYear;
import com.synectiks.cms.domain.enumeration.ClassSection;

import java.util.Objects;

public class AbstractSectionInput {
    private Long id;
    private ClassSection section;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassSection getSection() {
        return section;
    }

    public void setSection(ClassSection section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractSectionInput)) return false;
        AbstractSectionInput that = (AbstractSectionInput) o;
        return Objects.equals(getId(), that.getId()) &&
            getSection() == that.getSection();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSection());
    }

    @Override
    public String toString() {
        return "AbstractSectionInput{" +
            "id=" + id +
            ", section=" + section +
            '}';
    }
}
