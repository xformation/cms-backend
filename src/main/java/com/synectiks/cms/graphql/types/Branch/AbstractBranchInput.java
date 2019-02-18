package com.synectiks.cms.graphql.types.Branch;

import java.util.Objects;

public class AbstractBranchInput {
    private Long id;
    private String branchName;
    private String address1;
    private String address2;
    private String branchHead;

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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getBranchHead() {
        return branchHead;
    }

    public void setBranchHead(String branchHead) {
        this.branchHead = branchHead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBranchInput that = (AbstractBranchInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(branchName, that.branchName) &&
            Objects.equals(address1, that.address1) &&
            Objects.equals(address2, that.address2) &&
            Objects.equals(branchHead, that.branchHead);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, branchName, address1, address2, branchHead);
    }

    @Override
    public String toString() {
        return "AbstractBranchInput{" +
            "id=" + id +
            ", branchName='" + branchName + '\'' +
            ", address1='" + address1 + '\'' +
            ", address2='" + address2 + '\'' +
            ", branchHead='" + branchHead + '\'' +
            '}';
    }
}
