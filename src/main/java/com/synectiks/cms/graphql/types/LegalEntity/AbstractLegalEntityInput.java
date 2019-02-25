
package com.synectiks.cms.graphql.types.LegalEntity;

import java.util.Date;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.TypeOfCollege;

    public class AbstractLegalEntityInput {

        private Long id;
        private Long logo;
        private String legalNameOfTheCollege;
        private TypeOfCollege typeOfCollege;
        private Date dateOfIncorporation;
        private String registeredOfficeAddress1;
        private String registeredOfficeAddress2;
        private String registeredOfficeAddress3;
        private String registeredOfficeAddress4;
        private String registeredOfficeAddress5;
        private String collegeIdentificationNumber;
        private String pan;
        private String tan;
        private String tanCircleNumber;
        private String citTdsLocation;
        private Long formSignatory;
        private String pfNumber;
        private Date pfRegistrationDate;
        private Long pfSignatory;
//        private String pfSignatoryDesignation;
//        private String pfSignatoryFatherName;
        private Long esiNumber;
        private Date esiRegistrationDate;
        private Long esiSignatory;
//        private String esiSignatoryDesignation;
//        private String esiSignatoryFatherName;
        private Long ptNumber;
        private Date ptRegistrationDate;
        private Long ptSignatory;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getLogo() {
            return logo;
        }

        public void setLogo(Long logo) {
            this.logo = logo;
        }

        public String getLegalNameOfTheCollege() {
            return legalNameOfTheCollege;
        }

        public void setLegalNameOfTheCollege(String legalNameOfTheCollege) {
            this.legalNameOfTheCollege = legalNameOfTheCollege;
        }

        public TypeOfCollege getTypeOfCollege() {
            return typeOfCollege;
        }

        public void setTypeOfCollege(TypeOfCollege typeOfCollege) {
            this.typeOfCollege = typeOfCollege;
        }

        public Date getDateOfIncorporation() {
            return dateOfIncorporation;
        }

        public void setDateOfIncorporation(Date dateOfIncorporation) {
            this.dateOfIncorporation = dateOfIncorporation;
        }

        public String getRegisteredOfficeAddress1() {
            return registeredOfficeAddress1;
        }

        public void setRegisteredOfficeAddress1(String registeredOfficeAddress1) {
            this.registeredOfficeAddress1 = registeredOfficeAddress1;
        }

        public String getRegisteredOfficeAddress2() {
            return registeredOfficeAddress2;
        }

        public void setRegisteredOfficeAddress2(String registeredOfficeAddress2) {
            this.registeredOfficeAddress2 = registeredOfficeAddress2;
        }

        public String getRegisteredOfficeAddress3() {
            return registeredOfficeAddress3;
        }

        public void setRegisteredOfficeAddress3(String registeredOfficeAddress3) {
            this.registeredOfficeAddress3 = registeredOfficeAddress3;
        }

        public String getRegisteredOfficeAddress4() {
            return registeredOfficeAddress4;
        }

        public void setRegisteredOfficeAddress4(String registeredOfficeAddress4) {
            this.registeredOfficeAddress4 = registeredOfficeAddress4;
        }

        public String getRegisteredOfficeAddress5() {
            return registeredOfficeAddress5;
        }

        public void setRegisteredOfficeAddress5(String registeredOfficeAddress5) {
            this.registeredOfficeAddress5 = registeredOfficeAddress5;
        }

        public String getCollegeIdentificationNumber() {
            return collegeIdentificationNumber;
        }

        public void setCollegeIdentificationNumber(String collegeIdentificationNumber) {
            this.collegeIdentificationNumber = collegeIdentificationNumber;
        }

        public String getPan() {
            return pan;
        }

        public void setPan(String pan) {
            this.pan = pan;
        }

        public String getTan() {
            return tan;
        }

        public void setTan(String tan) {
            this.tan = tan;
        }

        public String getTanCircleNumber() {
            return tanCircleNumber;
        }

        public void setTanCircleNumber(String tanCircleNumber) {
            this.tanCircleNumber = tanCircleNumber;
        }

        public String getCitTdsLocation() {
            return citTdsLocation;
        }

        public void setCitTdsLocation(String citTdsLocation) {
            this.citTdsLocation = citTdsLocation;
        }

        public Long getFormSignatory() {
            return formSignatory;
        }

        public void setFormSignatory(Long formSignatory) {
            this.formSignatory = formSignatory;
        }

        public String getPfNumber() {
            return pfNumber;
        }

        public void setPfNumber(String pfNumber) {
            this.pfNumber = pfNumber;
        }

        public Date getPfRegistrationDate() {
            return pfRegistrationDate;
        }

        public void setPfRegistrationDate(Date pfRegistrationDate) {
            this.pfRegistrationDate = pfRegistrationDate;
        }

        public Long getPfSignatory() {
            return pfSignatory;
        }

        public void setPfSignatory(Long pfSignatory) {
            this.pfSignatory = pfSignatory;
        }

        public Long getEsiNumber() {
            return esiNumber;
        }

        public void setEsiNumber(Long esiNumber) {
            this.esiNumber = esiNumber;
        }

        public Date getEsiRegistrationDate() {
            return esiRegistrationDate;
        }

        public void setEsiRegistrationDate(Date esiRegistrationDate) {
            this.esiRegistrationDate = esiRegistrationDate;
        }

        public Long getEsiSignatory() {
            return esiSignatory;
        }

        public void setEsiSignatory(Long esiSignatory) {
            this.esiSignatory = esiSignatory;
        }

        public Long getPtNumber() {
            return ptNumber;
        }

        public void setPtNumber(Long ptNumber) {
            this.ptNumber = ptNumber;
        }

        public Date getPtRegistrationDate() {
            return ptRegistrationDate;
        }

        public void setPtRegistrationDate(Date ptRegistrationDate) {
            this.ptRegistrationDate = ptRegistrationDate;
        }

        public Long getPtSignatory() {
            return ptSignatory;
        }

        public void setPtSignatory(Long ptSignatory) {
            this.ptSignatory = ptSignatory;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AbstractLegalEntityInput that = (AbstractLegalEntityInput) o;
            return Objects.equals(id, that.id) &&
                Objects.equals(logo, that.logo) &&
                Objects.equals(legalNameOfTheCollege, that.legalNameOfTheCollege) &&
                typeOfCollege == that.typeOfCollege &&
                Objects.equals(dateOfIncorporation, that.dateOfIncorporation) &&
                Objects.equals(registeredOfficeAddress1, that.registeredOfficeAddress1) &&
                Objects.equals(registeredOfficeAddress2, that.registeredOfficeAddress2) &&
                Objects.equals(registeredOfficeAddress3, that.registeredOfficeAddress3) &&
                Objects.equals(registeredOfficeAddress4, that.registeredOfficeAddress4) &&
                Objects.equals(registeredOfficeAddress5, that.registeredOfficeAddress5) &&
                Objects.equals(collegeIdentificationNumber, that.collegeIdentificationNumber) &&
                Objects.equals(pan, that.pan) &&
                Objects.equals(tan, that.tan) &&
                Objects.equals(tanCircleNumber, that.tanCircleNumber) &&
                Objects.equals(citTdsLocation, that.citTdsLocation) &&
                Objects.equals(formSignatory, that.formSignatory) &&
                Objects.equals(pfNumber, that.pfNumber) &&
                Objects.equals(pfRegistrationDate, that.pfRegistrationDate) &&
                Objects.equals(pfSignatory, that.pfSignatory) &&
                Objects.equals(esiNumber, that.esiNumber) &&
                Objects.equals(esiRegistrationDate, that.esiRegistrationDate) &&
                Objects.equals(esiSignatory, that.esiSignatory) &&
                Objects.equals(ptNumber, that.ptNumber) &&
                Objects.equals(ptRegistrationDate, that.ptRegistrationDate) &&
                Objects.equals(ptSignatory, that.ptSignatory);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, logo, legalNameOfTheCollege, typeOfCollege, dateOfIncorporation, registeredOfficeAddress1, registeredOfficeAddress2, registeredOfficeAddress3, registeredOfficeAddress4, registeredOfficeAddress5, collegeIdentificationNumber, pan, tan, tanCircleNumber, citTdsLocation, formSignatory, pfNumber, pfRegistrationDate, pfSignatory, esiNumber, esiRegistrationDate, esiSignatory, ptNumber, ptRegistrationDate, ptSignatory);
        }

        @Override
        public String toString() {
            return "AbstractLegalEntityInput{" +
                "id=" + id +
                ", logo=" + logo +
                ", legalNameOfTheCollege='" + legalNameOfTheCollege + '\'' +
                ", typeOfCollege=" + typeOfCollege +
                ", dateOfIncorporation=" + dateOfIncorporation +
                ", registeredOfficeAddress1='" + registeredOfficeAddress1 + '\'' +
                ", registeredOfficeAddress2='" + registeredOfficeAddress2 + '\'' +
                ", registeredOfficeAddress3='" + registeredOfficeAddress3 + '\'' +
                ", registeredOfficeAddress4='" + registeredOfficeAddress4 + '\'' +
                ", registeredOfficeAddress5='" + registeredOfficeAddress5 + '\'' +
                ", collegeIdentificationNumber='" + collegeIdentificationNumber + '\'' +
                ", pan='" + pan + '\'' +
                ", tan='" + tan + '\'' +
                ", tanCircleNumber='" + tanCircleNumber + '\'' +
                ", citTdsLocation='" + citTdsLocation + '\'' +
                ", formSignatory=" + formSignatory +
                ", pfNumber='" + pfNumber + '\'' +
                ", pfRegistrationDate=" + pfRegistrationDate +
                ", pfSignatory=" + pfSignatory +
                ", esiNumber=" + esiNumber +
                ", esiRegistrationDate=" + esiRegistrationDate +
                ", esiSignatory=" + esiSignatory +
                ", ptNumber=" + ptNumber +
                ", ptRegistrationDate=" + ptRegistrationDate +
                ", ptSignatory=" + ptSignatory +
                '}';
        }
    }
