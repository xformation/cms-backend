package com.synectiks.cms.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.cms.entities.enumeration.TypeOfInsurance;

import java.time.LocalDate;

public class CmsInsurance implements Comparable<CmsInsurance> {
	private Long id;
	private String insuranceCompany;
	private TypeOfInsurance typeOfInsurance;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dateOfInsurance;
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate validTill;
	private String strDateOfInsurance;
	private String strValidTill;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public TypeOfInsurance getTypeOfInsurance() {
		return typeOfInsurance;
	}

	public void setTypeOfInsurance(TypeOfInsurance typeOfInsurance) {
		this.typeOfInsurance = typeOfInsurance;
	}

	public LocalDate getDateOfInsurance() {
		return dateOfInsurance;
	}

	public void setDateOfInsurance(LocalDate dateOfInsurance) {
		this.dateOfInsurance = dateOfInsurance;
	}

	public LocalDate getValidTill() {
		return validTill;
	}

	public void setValidTill(LocalDate validTill) {
		this.validTill = validTill;
	}

	public String getStrDateOfInsurance() {
		return strDateOfInsurance;
	}

	public void setStrDateOfInsurance(String strDateOfInsurance) {
		this.strDateOfInsurance = strDateOfInsurance;
	}

	public String getStrValidTill() {
		return strValidTill;
	}

	public void setStrValidTill(String strValidTill) {
		this.strValidTill = strValidTill;
	}

	@Override
	public int compareTo(CmsInsurance o) {
		return this.id.compareTo(o.getId());
	}
}
