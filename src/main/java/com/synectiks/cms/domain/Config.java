package com.synectiks.cms.domain;

import java.io.Serializable;
import java.util.List;

public class Config implements Serializable{

	private Country country;
	private State state;
	private City city;
	private College college;
	private Branch branch;
	private CmsAcademicYearVo cmsAcademicYearVo;
	private String loggedInUser;
	private String footerNote;
	private Object loginResponse;
	private List<Branch> branchList;
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	public String getLoggedInUser() {
		return loggedInUser;
	}
	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	public String getFooterNote() {
		return footerNote;
	}
	public void setFooterNote(String footerNote) {
		this.footerNote = footerNote;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loggedInUser == null) ? 0 : loggedInUser.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Config other = (Config) obj;
		if (loggedInUser == null) {
			if (other.loggedInUser != null)
				return false;
		} else if (!loggedInUser.equals(other.loggedInUser))
			return false;
		return true;
	}
	
	public Object getLoginResponse() {
		return loginResponse;
	}
	public void setLoginResponse(Object loginResponse) {
		this.loginResponse = loginResponse;
	}
	
	public List<Branch> getBranchList() {
		return branchList;
	}
	public void setBranchList(List<Branch> branchList) {
		this.branchList = branchList;
	}
	public CmsAcademicYearVo getCmsAcademicYearVo() {
		return cmsAcademicYearVo;
	}
	public void setCmsAcademicYearVo(CmsAcademicYearVo cmsAcademicYearVo) {
		this.cmsAcademicYearVo = cmsAcademicYearVo;
	}
	
	
}
