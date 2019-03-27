package com.synectiks.cms.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * A VO for the Branch entity.
 */
public class CmsBranchVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private String branchName;
    private String address1;
    private String address2;
    private String branchHead;
    private Long collegeId;
    private Long cityId;
    private Long stateId;
    private College college;
    private City city;
    private State state;

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

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CmsBranchVo branchDTO = (CmsBranchVo) o;
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
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", branchHead='" + getBranchHead() + "'" +
            ", college=" + getCollegeId() +
            ", city=" + getCityId() +
            ", state=" + getStateId() +
            "}";
    }

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
