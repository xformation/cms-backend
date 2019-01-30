package com.synectiks.cms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.TypeOfCollege;

/**
 * A LegalEntity.
 */
@Entity
@Table(name = "legal_entity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "legalentity")
public class LegalEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "logo", nullable = false)
    private Long logo;

    @NotNull
    @Column(name = "legal_name_of_the_college", nullable = false)
    private String legalNameOfTheCollege;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_college", nullable = false)
    private TypeOfCollege typeOfCollege;

    @NotNull
    @Column(name = "date_of_incorporation", nullable = false)
    private Date dateOfIncorporation;

    @NotNull
    @Column(name = "registered_office_address", nullable = false)
    private String registeredOfficeAddress;

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
    private String formSignatory;

    @NotNull
    @Column(name = "pf_number", nullable = false)
    private String pfNumber;

    @NotNull
    @Column(name = "pf_registration_date", nullable = false)
    private Date pfRegistrationDate;

    @NotNull
    @Column(name = "pf_signatory", nullable = false)
    private String pfSignatory;

    @NotNull
    @Column(name = "pf_signatory_designation", nullable = false)
    private String pfSignatoryDesignation;

    @NotNull
    @Column(name = "pf_signatory_father_name", nullable = false)
    private String pfSignatoryFatherName;

    @NotNull
    @Column(name = "esi_number", nullable = false)
    private Long esiNumber;

    @NotNull
    @Column(name = "esi_registration_date", nullable = false)
    private Date esiRegistrationDate;

    @NotNull
    @Column(name = "esi_signatory", nullable = false)
    private String esiSignatory;

    @NotNull
    @Column(name = "esi_signatory_designation", nullable = false)
    private String esiSignatoryDesignation;

    @NotNull
    @Column(name = "esi_signatory_father_name", nullable = false)
    private String esiSignatoryFatherName;

    @NotNull
    @Column(name = "pt_number", nullable = false)
    private Long ptNumber;

    @NotNull
    @Column(name = "pt_registration_date", nullable = false)
    private Date ptRegistrationDate;

    @NotNull
    @Column(name = "pt_signatory", nullable = false)
    private String ptSignatory;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLogo() {
        return logo;
    }

    public LegalEntity logo(Long logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(Long logo) {
        this.logo = logo;
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

    public Date getDateOfIncorporation() {
        return dateOfIncorporation;
    }

    public LegalEntity dateOfIncorporation(Date dateOfIncorporation) {
        this.dateOfIncorporation = dateOfIncorporation;
        return this;
    }

    public void setDateOfIncorporation(Date dateOfIncorporation) {
        this.dateOfIncorporation = dateOfIncorporation;
    }

    public String getRegisteredOfficeAddress() {
        return registeredOfficeAddress;
    }

    public LegalEntity registeredOfficeAddress(String registeredOfficeAddress) {
        this.registeredOfficeAddress = registeredOfficeAddress;
        return this;
    }

    public void setRegisteredOfficeAddress(String registeredOfficeAddress) {
        this.registeredOfficeAddress = registeredOfficeAddress;
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

    public String getFormSignatory() {
        return formSignatory;
    }

    public LegalEntity formSignatory(String formSignatory) {
        this.formSignatory = formSignatory;
        return this;
    }

    public void setFormSignatory(String formSignatory) {
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

    public Date getPfRegistrationDate() {
        return pfRegistrationDate;
    }

    public LegalEntity pfRegistrationDate(Date pfRegistrationDate) {
        this.pfRegistrationDate = pfRegistrationDate;
        return this;
    }

    public void setPfRegistrationDate(Date pfRegistrationDate) {
        this.pfRegistrationDate = pfRegistrationDate;
    }

    public String getPfSignatory() {
        return pfSignatory;
    }

    public LegalEntity pfSignatory(String pfSignatory) {
        this.pfSignatory = pfSignatory;
        return this;
    }

    public void setPfSignatory(String pfSignatory) {
        this.pfSignatory = pfSignatory;
    }

    public String getPfSignatoryDesignation() {
        return pfSignatoryDesignation;
    }

    public LegalEntity pfSignatoryDesignation(String pfSignatoryDesignation) {
        this.pfSignatoryDesignation = pfSignatoryDesignation;
        return this;
    }

    public void setPfSignatoryDesignation(String pfSignatoryDesignation) {
        this.pfSignatoryDesignation = pfSignatoryDesignation;
    }

    public String getPfSignatoryFatherName() {
        return pfSignatoryFatherName;
    }

    public LegalEntity pfSignatoryFatherName(String pfSignatoryFatherName) {
        this.pfSignatoryFatherName = pfSignatoryFatherName;
        return this;
    }

    public void setPfSignatoryFatherName(String pfSignatoryFatherName) {
        this.pfSignatoryFatherName = pfSignatoryFatherName;
    }

    public Long getEsiNumber() {
        return esiNumber;
    }

    public LegalEntity esiNumber(Long esiNumber) {
        this.esiNumber = esiNumber;
        return this;
    }

    public void setEsiNumber(Long esiNumber) {
        this.esiNumber = esiNumber;
    }

    public Date getEsiRegistrationDate() {
        return esiRegistrationDate;
    }

    public LegalEntity esiRegistrationDate(Date esiRegistrationDate) {
        this.esiRegistrationDate = esiRegistrationDate;
        return this;
    }

    public void setEsiRegistrationDate(Date esiRegistrationDate) {
        this.esiRegistrationDate = esiRegistrationDate;
    }

    public String getEsiSignatory() {
        return esiSignatory;
    }

    public LegalEntity esiSignatory(String esiSignatory) {
        this.esiSignatory = esiSignatory;
        return this;
    }

    public void setEsiSignatory(String esiSignatory) {
        this.esiSignatory = esiSignatory;
    }

    public String getEsiSignatoryDesignation() {
        return esiSignatoryDesignation;
    }

    public LegalEntity esiSignatoryDesignation(String esiSignatoryDesignation) {
        this.esiSignatoryDesignation = esiSignatoryDesignation;
        return this;
    }

    public void setEsiSignatoryDesignation(String esiSignatoryDesignation) {
        this.esiSignatoryDesignation = esiSignatoryDesignation;
    }

    public String getEsiSignatoryFatherName() {
        return esiSignatoryFatherName;
    }

    public LegalEntity esiSignatoryFatherName(String esiSignatoryFatherName) {
        this.esiSignatoryFatherName = esiSignatoryFatherName;
        return this;
    }

    public void setEsiSignatoryFatherName(String esiSignatoryFatherName) {
        this.esiSignatoryFatherName = esiSignatoryFatherName;
    }

    public Long getPtNumber() {
        return ptNumber;
    }

    public LegalEntity ptNumber(Long ptNumber) {
        this.ptNumber = ptNumber;
        return this;
    }

    public void setPtNumber(Long ptNumber) {
        this.ptNumber = ptNumber;
    }

    public Date getPtRegistrationDate() {
        return ptRegistrationDate;
    }

    public LegalEntity ptRegistrationDate(Date ptRegistrationDate) {
        this.ptRegistrationDate = ptRegistrationDate;
        return this;
    }

    public void setPtRegistrationDate(Date ptRegistrationDate) {
        this.ptRegistrationDate = ptRegistrationDate;
    }

    public String getPtSignatory() {
        return ptSignatory;
    }

    public LegalEntity ptSignatory(String ptSignatory) {
        this.ptSignatory = ptSignatory;
        return this;
    }

    public void setPtSignatory(String ptSignatory) {
        this.ptSignatory = ptSignatory;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

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
        return "LegalEntity{" +
            "id=" + getId() +
            ", logo=" + getLogo() +
            ", legalNameOfTheCollege='" + getLegalNameOfTheCollege() + "'" +
            ", typeOfCollege='" + getTypeOfCollege() + "'" +
            ", dateOfIncorporation='" + getDateOfIncorporation() + "'" +
            ", registeredOfficeAddress='" + getRegisteredOfficeAddress() + "'" +
            ", collegeIdentificationNumber='" + getCollegeIdentificationNumber() + "'" +
            ", pan='" + getPan() + "'" +
            ", tan='" + getTan() + "'" +
            ", tanCircleNumber='" + getTanCircleNumber() + "'" +
            ", citTdsLocation='" + getCitTdsLocation() + "'" +
            ", formSignatory='" + getFormSignatory() + "'" +
            ", pfNumber='" + getPfNumber() + "'" +
            ", pfRegistrationDate='" + getPfRegistrationDate() + "'" +
            ", pfSignatory='" + getPfSignatory() + "'" +
            ", pfSignatoryDesignation='" + getPfSignatoryDesignation() + "'" +
            ", pfSignatoryFatherName='" + getPfSignatoryFatherName() + "'" +
            ", esiNumber=" + getEsiNumber() +
            ", esiRegistrationDate='" + getEsiRegistrationDate() + "'" +
            ", esiSignatory='" + getEsiSignatory() + "'" +
            ", esiSignatoryDesignation='" + getEsiSignatoryDesignation() + "'" +
            ", esiSignatoryFatherName='" + getEsiSignatoryFatherName() + "'" +
            ", ptNumber=" + getPtNumber() +
            ", ptRegistrationDate='" + getPtRegistrationDate() + "'" +
            ", ptSignatory='" + getPtSignatory() + "'" +
            "}";
    }
}
