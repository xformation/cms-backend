package com.synectiks.cms.graphql.types.FeeCategory;

import java.util.Objects;

public class AbstractFeeCategoryInput {
    private Long id;
    private String  categoryName;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractFeeCategoryInput)) return false;
        AbstractFeeCategoryInput that = (AbstractFeeCategoryInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getCategoryName(), that.getCategoryName()) &&
            Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCategoryName(), getDescription());
    }

    @Override
    public String toString() {
        return "AbstractFeeCategoryInput{" +
            "id=" + id +
            ", categoryName='" + categoryName + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
