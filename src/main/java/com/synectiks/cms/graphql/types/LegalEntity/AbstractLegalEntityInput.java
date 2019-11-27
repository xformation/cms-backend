
package com.synectiks.cms.graphql.types.LegalEntity;

import java.util.Date;

import com.synectiks.cms.domain.enumeration.TypeOfCollege;

    public class AbstractLegalEntityInput {

        private Long id;
        private String logoPath;
        private String logoFileName;
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
        private String esiNumber;
        private Date esiRegistrationDate;
        private Long esiSignatory;
//        private String esiSignatoryDesignation;
//        private String esiSignatoryFatherName;
        private String ptNumber;
        private Date ptRegistrationDate;
        private Long ptSignatory;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public String getEsiNumber() {
            return esiNumber;
        }

        public void setEsiNumber(String esiNumber) {
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

        public String getPtNumber() {
            return ptNumber;
        }

        public void setPtNumber(String ptNumber) {
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

		public String getLogoPath() {
			return logoPath;
		}

		public void setLogoPath(String logoPath) {
			this.logoPath = logoPath;
		}

		public String getLogoFileName() {
			return logoFileName;
		}

		public void setLogoFileName(String logoFileName) {
			this.logoFileName = logoFileName;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((citTdsLocation == null) ? 0 : citTdsLocation.hashCode());
			result = prime * result
					+ ((collegeIdentificationNumber == null) ? 0 : collegeIdentificationNumber.hashCode());
			result = prime * result + ((dateOfIncorporation == null) ? 0 : dateOfIncorporation.hashCode());
			result = prime * result + ((esiNumber == null) ? 0 : esiNumber.hashCode());
			result = prime * result + ((esiRegistrationDate == null) ? 0 : esiRegistrationDate.hashCode());
			result = prime * result + ((esiSignatory == null) ? 0 : esiSignatory.hashCode());
			result = prime * result + ((formSignatory == null) ? 0 : formSignatory.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((legalNameOfTheCollege == null) ? 0 : legalNameOfTheCollege.hashCode());
			result = prime * result + ((logoFileName == null) ? 0 : logoFileName.hashCode());
			result = prime * result + ((logoPath == null) ? 0 : logoPath.hashCode());
			result = prime * result + ((pan == null) ? 0 : pan.hashCode());
			result = prime * result + ((pfNumber == null) ? 0 : pfNumber.hashCode());
			result = prime * result + ((pfRegistrationDate == null) ? 0 : pfRegistrationDate.hashCode());
			result = prime * result + ((pfSignatory == null) ? 0 : pfSignatory.hashCode());
			result = prime * result + ((ptNumber == null) ? 0 : ptNumber.hashCode());
			result = prime * result + ((ptRegistrationDate == null) ? 0 : ptRegistrationDate.hashCode());
			result = prime * result + ((ptSignatory == null) ? 0 : ptSignatory.hashCode());
			result = prime * result + ((registeredOfficeAddress1 == null) ? 0 : registeredOfficeAddress1.hashCode());
			result = prime * result + ((registeredOfficeAddress2 == null) ? 0 : registeredOfficeAddress2.hashCode());
			result = prime * result + ((registeredOfficeAddress3 == null) ? 0 : registeredOfficeAddress3.hashCode());
			result = prime * result + ((registeredOfficeAddress4 == null) ? 0 : registeredOfficeAddress4.hashCode());
			result = prime * result + ((registeredOfficeAddress5 == null) ? 0 : registeredOfficeAddress5.hashCode());
			result = prime * result + ((tan == null) ? 0 : tan.hashCode());
			result = prime * result + ((tanCircleNumber == null) ? 0 : tanCircleNumber.hashCode());
			result = prime * result + ((typeOfCollege == null) ? 0 : typeOfCollege.hashCode());
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
			AbstractLegalEntityInput other = (AbstractLegalEntityInput) obj;
			if (citTdsLocation == null) {
				if (other.citTdsLocation != null)
					return false;
			} else if (!citTdsLocation.equals(other.citTdsLocation))
				return false;
			if (collegeIdentificationNumber == null) {
				if (other.collegeIdentificationNumber != null)
					return false;
			} else if (!collegeIdentificationNumber.equals(other.collegeIdentificationNumber))
				return false;
			if (dateOfIncorporation == null) {
				if (other.dateOfIncorporation != null)
					return false;
			} else if (!dateOfIncorporation.equals(other.dateOfIncorporation))
				return false;
			if (esiNumber == null) {
				if (other.esiNumber != null)
					return false;
			} else if (!esiNumber.equals(other.esiNumber))
				return false;
			if (esiRegistrationDate == null) {
				if (other.esiRegistrationDate != null)
					return false;
			} else if (!esiRegistrationDate.equals(other.esiRegistrationDate))
				return false;
			if (esiSignatory == null) {
				if (other.esiSignatory != null)
					return false;
			} else if (!esiSignatory.equals(other.esiSignatory))
				return false;
			if (formSignatory == null) {
				if (other.formSignatory != null)
					return false;
			} else if (!formSignatory.equals(other.formSignatory))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (legalNameOfTheCollege == null) {
				if (other.legalNameOfTheCollege != null)
					return false;
			} else if (!legalNameOfTheCollege.equals(other.legalNameOfTheCollege))
				return false;
			if (logoFileName == null) {
				if (other.logoFileName != null)
					return false;
			} else if (!logoFileName.equals(other.logoFileName))
				return false;
			if (logoPath == null) {
				if (other.logoPath != null)
					return false;
			} else if (!logoPath.equals(other.logoPath))
				return false;
			if (pan == null) {
				if (other.pan != null)
					return false;
			} else if (!pan.equals(other.pan))
				return false;
			if (pfNumber == null) {
				if (other.pfNumber != null)
					return false;
			} else if (!pfNumber.equals(other.pfNumber))
				return false;
			if (pfRegistrationDate == null) {
				if (other.pfRegistrationDate != null)
					return false;
			} else if (!pfRegistrationDate.equals(other.pfRegistrationDate))
				return false;
			if (pfSignatory == null) {
				if (other.pfSignatory != null)
					return false;
			} else if (!pfSignatory.equals(other.pfSignatory))
				return false;
			if (ptNumber == null) {
				if (other.ptNumber != null)
					return false;
			} else if (!ptNumber.equals(other.ptNumber))
				return false;
			if (ptRegistrationDate == null) {
				if (other.ptRegistrationDate != null)
					return false;
			} else if (!ptRegistrationDate.equals(other.ptRegistrationDate))
				return false;
			if (ptSignatory == null) {
				if (other.ptSignatory != null)
					return false;
			} else if (!ptSignatory.equals(other.ptSignatory))
				return false;
			if (registeredOfficeAddress1 == null) {
				if (other.registeredOfficeAddress1 != null)
					return false;
			} else if (!registeredOfficeAddress1.equals(other.registeredOfficeAddress1))
				return false;
			if (registeredOfficeAddress2 == null) {
				if (other.registeredOfficeAddress2 != null)
					return false;
			} else if (!registeredOfficeAddress2.equals(other.registeredOfficeAddress2))
				return false;
			if (registeredOfficeAddress3 == null) {
				if (other.registeredOfficeAddress3 != null)
					return false;
			} else if (!registeredOfficeAddress3.equals(other.registeredOfficeAddress3))
				return false;
			if (registeredOfficeAddress4 == null) {
				if (other.registeredOfficeAddress4 != null)
					return false;
			} else if (!registeredOfficeAddress4.equals(other.registeredOfficeAddress4))
				return false;
			if (registeredOfficeAddress5 == null) {
				if (other.registeredOfficeAddress5 != null)
					return false;
			} else if (!registeredOfficeAddress5.equals(other.registeredOfficeAddress5))
				return false;
			if (tan == null) {
				if (other.tan != null)
					return false;
			} else if (!tan.equals(other.tan))
				return false;
			if (tanCircleNumber == null) {
				if (other.tanCircleNumber != null)
					return false;
			} else if (!tanCircleNumber.equals(other.tanCircleNumber))
				return false;
			if (typeOfCollege != other.typeOfCollege)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "AbstractLegalEntityInput [id=" + id + ", logoPath=" + logoPath + ", logoFileName=" + logoFileName
					+ ", legalNameOfTheCollege=" + legalNameOfTheCollege + ", typeOfCollege=" + typeOfCollege
					+ ", dateOfIncorporation=" + dateOfIncorporation + ", registeredOfficeAddress1="
					+ registeredOfficeAddress1 + ", registeredOfficeAddress2=" + registeredOfficeAddress2
					+ ", registeredOfficeAddress3=" + registeredOfficeAddress3 + ", registeredOfficeAddress4="
					+ registeredOfficeAddress4 + ", registeredOfficeAddress5=" + registeredOfficeAddress5
					+ ", collegeIdentificationNumber=" + collegeIdentificationNumber + ", pan=" + pan + ", tan=" + tan
					+ ", tanCircleNumber=" + tanCircleNumber + ", citTdsLocation=" + citTdsLocation + ", formSignatory="
					+ formSignatory + ", pfNumber=" + pfNumber + ", pfRegistrationDate=" + pfRegistrationDate
					+ ", pfSignatory=" + pfSignatory + ", esiNumber=" + esiNumber + ", esiRegistrationDate="
					+ esiRegistrationDate + ", esiSignatory=" + esiSignatory + ", ptNumber=" + ptNumber
					+ ", ptRegistrationDate=" + ptRegistrationDate + ", ptSignatory=" + ptSignatory + "]";
		}

        
    }
