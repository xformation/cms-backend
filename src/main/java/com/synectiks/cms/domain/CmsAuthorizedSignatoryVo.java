package com.synectiks.cms.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * A VO for the AuthorizedSignatory entity.
 */
public class CmsAuthorizedSignatoryVo implements Serializable {

    private Long id;
    private String signatoryName;
    private String signatoryFatherName;
    private String signatoryDesignation;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String email;
    private String panCardNumber;
    private Long branchId;
    private Long collegeId;
    private Branch branch;
    private College college;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSignatoryName() {
        return signatoryName;
    }

    public void setSignatoryName(String signatoryName) {
        this.signatoryName = signatoryName;
    }

    public String getSignatoryFatherName() {
        return signatoryFatherName;
    }

    public void setSignatoryFatherName(String signatoryFatherName) {
        this.signatoryFatherName = signatoryFatherName;
    }

    public String getSignatoryDesignation() {
        return signatoryDesignation;
    }

    public void setSignatoryDesignation(String signatoryDesignation) {
        this.signatoryDesignation = signatoryDesignation;
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

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getAddress5() {
        return address5;
    }

    public void setAddress5(String address5) {
        this.address5 = address5;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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

        CmsAuthorizedSignatoryVo authorizedSignatoryDTO = (CmsAuthorizedSignatoryVo) o;
        if (authorizedSignatoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), authorizedSignatoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuthorizedSignatoryDTO{" +
            "id=" + getId() +
            ", signatoryName='" + getSignatoryName() + "'" +
            ", signatoryFatherName='" + getSignatoryFatherName() + "'" +
            ", signatoryDesignation='" + getSignatoryDesignation() + "'" +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", address3='" + getAddress3() + "'" +
            ", address4='" + getAddress4() + "'" +
            ", address5='" + getAddress5() + "'" +
            ", email='" + getEmail() + "'" +
            ", panCardNumber='" + getPanCardNumber() + "'" +
            ", branch=" + getBranchId() +
            ", college=" + getCollegeId() +
            "}";
    }

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}
}
