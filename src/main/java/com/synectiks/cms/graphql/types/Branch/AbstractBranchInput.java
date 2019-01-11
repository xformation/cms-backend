package com.synectiks.cms.graphql.types.Branch;

import java.util.Objects;

public class AbstractBranchInput {
    private Long id;
    private String branchName;
    private String description;
    private String collegeHead;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBranchInput)) return false;
        AbstractBranchInput that = (AbstractBranchInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getBranchName(), that.getBranchName()) &&
            Objects.equals(getDescription(), that.getDescription()) &&
            Objects.equals(getCollegeHead(), that.getCollegeHead());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBranchName(), getDescription(), getCollegeHead());
    }

    @Override
    public String toString() {
        return "AbstractCollegeBranchesInput{" +
            "id=" + id +
            ", branchName='" + branchName + '\'' +
            ", description='" + description + '\'' +
            ", collegeHead='" + collegeHead + '\'' +
            '}';
    }
}
