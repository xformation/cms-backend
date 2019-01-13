package com.synectiks.cms.graphql.types.CourseOffer;

import java.util.Objects;

public class AbstractCourseOfferInput {
	private Long id;
    private String desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCourseOfferInput that = (AbstractCourseOfferInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desc);
    }

    @Override
    public String toString() {
        return "AbstractCourseOfferInput{" +
            "id=" + id +
            ", desc='" + desc + '\'' +
            '}';
    }
}
