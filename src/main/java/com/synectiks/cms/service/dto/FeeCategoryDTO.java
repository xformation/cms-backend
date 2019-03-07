package com.synectiks.cms.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FeeCategory entity.
 */
public class FeeCategoryDTO implements Serializable {

    private Long id;

    @NotNull
    private String categoryName;

    @NotNull
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeeCategoryDTO feeCategoryDTO = (FeeCategoryDTO) o;
        if (feeCategoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feeCategoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeeCategoryDTO{" +
            "id=" + getId() +
            ", categoryName='" + getCategoryName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
