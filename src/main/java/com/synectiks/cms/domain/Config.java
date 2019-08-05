package com.synectiks.cms.domain;

import java.io.Serializable;

public class Config implements Serializable{

	private Country country;
	private State state;
	private City city;
	private College college;
	private Branch branch;
	private AcademicYear academicYear;
	private String loggedInUser;
	private String footerNote;
	private Object loginResponse;
	
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
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
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
	@Override
	public String toString() {
		return "Config [country=" + country + ", state=" + state + ", city=" + city + ", college=" + college
				+ ", branch=" + branch + ", academicYear=" + academicYear + ", loggedInUser=" + loggedInUser
				+ ", footerNote=" + footerNote + ", loginResponse=" + loginResponse + "]";
	}
	
	
}
