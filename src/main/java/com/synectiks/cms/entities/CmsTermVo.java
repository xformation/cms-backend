package com.synectiks.cms.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.cms.entities.enumeration.Status;

public class CmsTermVo {

	private Long id;
	private String termsDesc;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate startDate;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate endDate;
	private Status termStatus;
	private AcademicYear academicyear;

	private String strStartDate;
	private String strEndDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTermsDesc() {
		return termsDesc;
	}

	public void setTermsDesc(String termsDesc) {
		this.termsDesc = termsDesc;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Status getTermStatus() {
		return termStatus;
	}

	public void setTermStatus(Status termStatus) {
		this.termStatus = termStatus;
	}

	public AcademicYear getAcademicyear() {
		return academicyear;
	}

	public void setAcademicyear(AcademicYear academicyear) {
		this.academicyear = academicyear;
	}

	public String getStrStartDate() {
		return strStartDate;
	}

	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}

	public String getStrEndDate() {
		return strEndDate;
	}

	public void setStrEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CmsTermVo cmsTermVo = (CmsTermVo) o;
		return Objects.equals(id, cmsTermVo.id)
				&& Objects.equals(termsDesc, cmsTermVo.termsDesc)
				&& Objects.equals(startDate, cmsTermVo.startDate)
				&& Objects.equals(endDate, cmsTermVo.endDate)
				&& termStatus == cmsTermVo.termStatus
				&& Objects.equals(academicyear, cmsTermVo.academicyear)
				&& Objects.equals(strStartDate, cmsTermVo.strStartDate)
				&& Objects.equals(strEndDate, cmsTermVo.strEndDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, termsDesc, startDate, endDate, termStatus, academicyear,
				strStartDate, strEndDate);
	}

	@Override
	public String toString() {
		return "CmsTermVo{" + "id=" + id + ", termsDesc='" + termsDesc + '\''
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", termStatus="
				+ termStatus + ", academicyear=" + academicyear + ", strStartDate='"
				+ strStartDate + '\'' + ", strEndDate='" + strEndDate + '\'' + '}';
	}
}
