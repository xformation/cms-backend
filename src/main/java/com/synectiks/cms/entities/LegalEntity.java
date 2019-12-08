package com.synectiks.cms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

//import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.synectiks.cms.entities.enumeration.TypeOfCollege;
import com.synectiks.cms.utils.IESEntity;

/**
 * A LegalEntity.
 */
@Entity
@Table(name = "legal_entity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
// @Document(indexName = "legalentity")
public class LegalEntity implements Serializable, IESEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@Column(name = "logo_path")
	private String logoPath;

	@Column(name = "logo_file_name")
	private String logoFileName;

	@Column(name = "logo_file")
	private String logoFile;

	@NotNull
	@Column(name = "legal_name_of_the_college", nullable = false)
	private String legalNameOfTheCollege;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "type_of_college", nullable = false)
	private TypeOfCollege typeOfCollege;

	@NotNull
	@Column(name = "date_of_incorporation", nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dateOfIncorporation;

	@NotNull
	@Column(name = "registered_office_address_1", nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private String registeredOfficeAddress1;

	@Column(name = "registered_office_address_2")
	private String registeredOfficeAddress2;

	@Column(name = "registered_office_address_3")
	private String registeredOfficeAddress3;

	@Column(name = "registered_office_address_4")
	private String registeredOfficeAddress4;

	@Column(name = "registered_office_address_5")
	private String registeredOfficeAddress5;

	@NotNull
	@Column(name = "college_identification_number", nullable = false)
	private String collegeIdentificationNumber;

	@NotNull
	@Column(name = "pan", nullable = false)
	private String pan;

	@NotNull
	@Column(name = "tan", nullable = false)
	private String tan;

	@NotNull
	@Column(name = "tan_circle_number", nullable = false)
	private String tanCircleNumber;

	@NotNull
	@Column(name = "cit_tds_location", nullable = false)
	private String citTdsLocation;

	@NotNull
	@Column(name = "form_signatory", nullable = false)
	private Long formSignatory;

	@NotNull
	@Column(name = "pf_number", nullable = false)
	private String pfNumber;

	@NotNull
	@Column(name = "pf_registration_date", nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate pfRegistrationDate;

	@NotNull
	@Column(name = "pf_signatory", nullable = false)
	private Long pfSignatory;

	@NotNull
	@Column(name = "esi_number", nullable = false)
	private String esiNumber;

	@NotNull
	@Column(name = "esi_registration_date", nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate esiRegistrationDate;

	@NotNull
	@Column(name = "esi_signatory", nullable = false)
	private Long esiSignatory;

	@NotNull
	@Column(name = "pt_number", nullable = false)
	private String ptNumber;

	@NotNull
	@Column(name = "pt_registration_date", nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate ptRegistrationDate;

	@NotNull
	@Column(name = "pt_signatory", nullable = false)
	private Long ptSignatory;

	@ManyToOne
	@JsonIgnoreProperties("legalEntities")
	private Branch branch;

	@ManyToOne
	@JsonIgnoreProperties("legalEntities")
	private College college;

	@ManyToOne
	@JsonIgnoreProperties("legalEntities")
	private State state;

	@ManyToOne
	@JsonIgnoreProperties("legalEntities")
	private City city;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public LegalEntity logoPath(String logoPath) {
		this.logoPath = logoPath;
		return this;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public LegalEntity logoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
		return this;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public LegalEntity logoFile(String logoFile) {
		this.logoFile = logoFile;
		return this;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	public String getLegalNameOfTheCollege() {
		return legalNameOfTheCollege;
	}

	public LegalEntity legalNameOfTheCollege(String legalNameOfTheCollege) {
		this.legalNameOfTheCollege = legalNameOfTheCollege;
		return this;
	}

	public void setLegalNameOfTheCollege(String legalNameOfTheCollege) {
		this.legalNameOfTheCollege = legalNameOfTheCollege;
	}

	public TypeOfCollege getTypeOfCollege() {
		return typeOfCollege;
	}

	public LegalEntity typeOfCollege(TypeOfCollege typeOfCollege) {
		this.typeOfCollege = typeOfCollege;
		return this;
	}

	public void setTypeOfCollege(TypeOfCollege typeOfCollege) {
		this.typeOfCollege = typeOfCollege;
	}

	public LocalDate getDateOfIncorporation() {
		return dateOfIncorporation;
	}

	public LegalEntity dateOfIncorporation(LocalDate dateOfIncorporation) {
		this.dateOfIncorporation = dateOfIncorporation;
		return this;
	}

	public void setDateOfIncorporation(LocalDate dateOfIncorporation) {
		this.dateOfIncorporation = dateOfIncorporation;
	}

	public String getRegisteredOfficeAddress1() {
		return registeredOfficeAddress1;
	}

	public LegalEntity registeredOfficeAddress1(String registeredOfficeAddress1) {
		this.registeredOfficeAddress1 = registeredOfficeAddress1;
		return this;
	}

	public void setRegisteredOfficeAddress1(String registeredOfficeAddress1) {
		this.registeredOfficeAddress1 = registeredOfficeAddress1;
	}

	public String getRegisteredOfficeAddress2() {
		return registeredOfficeAddress2;
	}

	public LegalEntity registeredOfficeAddress2(String registeredOfficeAddress2) {
		this.registeredOfficeAddress2 = registeredOfficeAddress2;
		return this;
	}

	public void setRegisteredOfficeAddress2(String registeredOfficeAddress2) {
		this.registeredOfficeAddress2 = registeredOfficeAddress2;
	}

	public String getRegisteredOfficeAddress3() {
		return registeredOfficeAddress3;
	}

	public LegalEntity registeredOfficeAddress3(String registeredOfficeAddress3) {
		this.registeredOfficeAddress3 = registeredOfficeAddress3;
		return this;
	}

	public void setRegisteredOfficeAddress3(String registeredOfficeAddress3) {
		this.registeredOfficeAddress3 = registeredOfficeAddress3;
	}

	public String getRegisteredOfficeAddress4() {
		return registeredOfficeAddress4;
	}

	public LegalEntity registeredOfficeAddress4(String registeredOfficeAddress4) {
		this.registeredOfficeAddress4 = registeredOfficeAddress4;
		return this;
	}

	public void setRegisteredOfficeAddress4(String registeredOfficeAddress4) {
		this.registeredOfficeAddress4 = registeredOfficeAddress4;
	}

	public String getRegisteredOfficeAddress5() {
		return registeredOfficeAddress5;
	}

	public LegalEntity registeredOfficeAddress5(String registeredOfficeAddress5) {
		this.registeredOfficeAddress5 = registeredOfficeAddress5;
		return this;
	}

	public void setRegisteredOfficeAddress5(String registeredOfficeAddress5) {
		this.registeredOfficeAddress5 = registeredOfficeAddress5;
	}

	public String getCollegeIdentificationNumber() {
		return collegeIdentificationNumber;
	}

	public LegalEntity collegeIdentificationNumber(String collegeIdentificationNumber) {
		this.collegeIdentificationNumber = collegeIdentificationNumber;
		return this;
	}

	public void setCollegeIdentificationNumber(String collegeIdentificationNumber) {
		this.collegeIdentificationNumber = collegeIdentificationNumber;
	}

	public String getPan() {
		return pan;
	}

	public LegalEntity pan(String pan) {
		this.pan = pan;
		return this;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getTan() {
		return tan;
	}

	public LegalEntity tan(String tan) {
		this.tan = tan;
		return this;
	}

	public void setTan(String tan) {
		this.tan = tan;
	}

	public String getTanCircleNumber() {
		return tanCircleNumber;
	}

	public LegalEntity tanCircleNumber(String tanCircleNumber) {
		this.tanCircleNumber = tanCircleNumber;
		return this;
	}

	public void setTanCircleNumber(String tanCircleNumber) {
		this.tanCircleNumber = tanCircleNumber;
	}

	public String getCitTdsLocation() {
		return citTdsLocation;
	}

	public LegalEntity citTdsLocation(String citTdsLocation) {
		this.citTdsLocation = citTdsLocation;
		return this;
	}

	public void setCitTdsLocation(String citTdsLocation) {
		this.citTdsLocation = citTdsLocation;
	}

	public Long getFormSignatory() {
		return formSignatory;
	}

	public LegalEntity formSignatory(Long formSignatory) {
		this.formSignatory = formSignatory;
		return this;
	}

	public void setFormSignatory(Long formSignatory) {
		this.formSignatory = formSignatory;
	}

	public String getPfNumber() {
		return pfNumber;
	}

	public LegalEntity pfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
		return this;
	}

	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}

	public LocalDate getPfRegistrationDate() {
		return pfRegistrationDate;
	}

	public LegalEntity pfRegistrationDate(LocalDate pfRegistrationDate) {
		this.pfRegistrationDate = pfRegistrationDate;
		return this;
	}

	public void setPfRegistrationDate(LocalDate pfRegistrationDate) {
		this.pfRegistrationDate = pfRegistrationDate;
	}

	public Long getPfSignatory() {
		return pfSignatory;
	}

	public LegalEntity pfSignatory(Long pfSignatory) {
		this.pfSignatory = pfSignatory;
		return this;
	}

	public void setPfSignatory(Long pfSignatory) {
		this.pfSignatory = pfSignatory;
	}

	public String getEsiNumber() {
		return esiNumber;
	}

	public LegalEntity esiNumber(String esiNumber) {
		this.esiNumber = esiNumber;
		return this;
	}

	public void setEsiNumber(String esiNumber) {
		this.esiNumber = esiNumber;
	}

	public LocalDate getEsiRegistrationDate() {
		return esiRegistrationDate;
	}

	public LegalEntity esiRegistrationDate(LocalDate esiRegistrationDate) {
		this.esiRegistrationDate = esiRegistrationDate;
		return this;
	}

	public void setEsiRegistrationDate(LocalDate esiRegistrationDate) {
		this.esiRegistrationDate = esiRegistrationDate;
	}

	public Long getEsiSignatory() {
		return esiSignatory;
	}

	public LegalEntity esiSignatory(Long esiSignatory) {
		this.esiSignatory = esiSignatory;
		return this;
	}

	public void setEsiSignatory(Long esiSignatory) {
		this.esiSignatory = esiSignatory;
	}

	public String getPtNumber() {
		return ptNumber;
	}

	public LegalEntity ptNumber(String ptNumber) {
		this.ptNumber = ptNumber;
		return this;
	}

	public void setPtNumber(String ptNumber) {
		this.ptNumber = ptNumber;
	}

	public LocalDate getPtRegistrationDate() {
		return ptRegistrationDate;
	}

	public LegalEntity ptRegistrationDate(LocalDate ptRegistrationDate) {
		this.ptRegistrationDate = ptRegistrationDate;
		return this;
	}

	public void setPtRegistrationDate(LocalDate ptRegistrationDate) {
		this.ptRegistrationDate = ptRegistrationDate;
	}

	public Long getPtSignatory() {
		return ptSignatory;
	}

	public LegalEntity ptSignatory(Long ptSignatory) {
		this.ptSignatory = ptSignatory;
		return this;
	}

	public void setPtSignatory(Long ptSignatory) {
		this.ptSignatory = ptSignatory;
	}

	public Branch getBranch() {
		return branch;
	}

	public LegalEntity branch(Branch branch) {
		this.branch = branch;
		return this;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public College getCollege() {
		return college;
	}

	public LegalEntity college(College college) {
		this.college = college;
		return this;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public State getState() {
		return state;
	}

	public LegalEntity state(State state) {
		this.state = state;
		return this;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public LegalEntity city(City city) {
		this.city = city;
		return this;
	}

	public void setCity(City city) {
		this.city = city;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters
	// and setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LegalEntity legalEntity = (LegalEntity) o;
		if (legalEntity.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), legalEntity.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "LegalEntity{" + "id=" + getId() + ", logoPath='" + getLogoPath() + "'"
				+ ", logoFileName='" + getLogoFileName() + "'" + ", logoFile='"
				+ getLogoFile() + "'" + ", legalNameOfTheCollege='"
				+ getLegalNameOfTheCollege() + "'" + ", typeOfCollege='"
				+ getTypeOfCollege() + "'" + ", dateOfIncorporation='"
				+ getDateOfIncorporation() + "'" + ", registeredOfficeAddress1='"
				+ getRegisteredOfficeAddress1() + "'" + ", registeredOfficeAddress2='"
				+ getRegisteredOfficeAddress2() + "'" + ", registeredOfficeAddress3='"
				+ getRegisteredOfficeAddress3() + "'" + ", registeredOfficeAddress4='"
				+ getRegisteredOfficeAddress4() + "'" + ", registeredOfficeAddress5='"
				+ getRegisteredOfficeAddress5() + "'" + ", collegeIdentificationNumber='"
				+ getCollegeIdentificationNumber() + "'" + ", pan='" + getPan() + "'"
				+ ", tan='" + getTan() + "'" + ", tanCircleNumber='"
				+ getTanCircleNumber() + "'" + ", citTdsLocation='" + getCitTdsLocation()
				+ "'" + ", formSignatory=" + getFormSignatory() + ", pfNumber='"
				+ getPfNumber() + "'" + ", pfRegistrationDate='" + getPfRegistrationDate()
				+ "'" + ", pfSignatory=" + getPfSignatory() + ", esiNumber='"
				+ getEsiNumber() + "'" + ", esiRegistrationDate='"
				+ getEsiRegistrationDate() + "'" + ", esiSignatory=" + getEsiSignatory()
				+ ", ptNumber='" + getPtNumber() + "'" + ", ptRegistrationDate='"
				+ getPtRegistrationDate() + "'" + ", ptSignatory=" + getPtSignatory()
				+ "}";
	}
}
