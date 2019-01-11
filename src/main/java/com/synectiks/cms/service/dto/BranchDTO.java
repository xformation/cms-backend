package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Branch entity.
 */
public class BranchDTO implements Serializable {

    private Long id;

    @NotNull
    private String branchName;

    @NotNull
    private String description;

    @NotNull
    private String collegeHead;

    private Long collegeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCollegeHead() {
        return collegeHead;
    }

    public void setCollegeHead(String collegeHead) {
        this.collegeHead = collegeHead;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BranchDTO branchDTO = (BranchDTO) o;
        if (branchDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), branchDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BranchDTO{" +
            "id=" + getId() +
            ", branchName='" + getBranchName() + "'" +
            ", description='" + getDescription() + "'" +
            ", collegeHead='" + getCollegeHead() + "'" +
            ", college=" + getCollegeId() +
            ", college=" + getCollegeId() +
            "}";
    }
}
