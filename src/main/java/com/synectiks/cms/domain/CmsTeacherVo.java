package com.synectiks.cms.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.Religion;
import com.synectiks.cms.domain.enumeration.Caste;
import com.synectiks.cms.domain.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synectiks.cms.domain.enumeration.Bloodgroup;
import com.synectiks.cms.domain.enumeration.RelationWithStudentEnum;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.StaffType;

/**
 * A Vo for the Teacher entity.
 */
public class CmsTeacherVo implements Serializable {

    private Long id;

    
    private String teacherName;

    
    private String teacherMiddleName;

    
    private String teacherLastName;

    
    private String fatherName;

    
    private String fatherMiddleName;

    
    private String fatherLastName;

    private String spouseName;

    private String spouseMiddleName;

    private String spouseLastName;

    
    private String motherName;

    
    private String motherMiddleName;

    
    private String motherLastName;

    
    private Long aadharNo;

    
    private LocalDate dateOfBirth;

    
    private String placeOfBirth;

    
    private Religion religion;

    
    private Caste caste;

    
    private String subCaste;

    
    private Integer age;

    
    private Gender sex;

    
    private Bloodgroup bloodGroup;

    
    private String addressLineOne;

    
    private String addressLineTwo;

    
    private String addressLineThree;

    
    private String town;

    
    private String state;

    
    private String country;

    
    private Long pincode;

    
    private String teacherContactNumber;

    private String alternateContactNumber;

    
    private String teacherEmailAddress;

    private String alternateEmailAddress;

    
    private RelationWithStudentEnum relationWithStaff;

    
    private String emergencyContactName;

    private String emergencyContactMiddleName;

    
    private String emergencyContactLastName;

    
    private String emergencyContactNo;

    
    private String emergencyContactEmailAddress;

    
    private String uploadPhoto;

    
    private Status status;

    private Long employeeId;

    
    private String designation;

    
    private StaffType staffType;

    private Long departmentId;

    private Long branchId;

    private Department department;
    private Branch branch;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherMiddleName() {
        return teacherMiddleName;
    }

    public void setTeacherMiddleName(String teacherMiddleName) {
        this.teacherMiddleName = teacherMiddleName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherMiddleName() {
        return fatherMiddleName;
    }

    public void setFatherMiddleName(String fatherMiddleName) {
        this.fatherMiddleName = fatherMiddleName;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getSpouseMiddleName() {
        return spouseMiddleName;
    }

    public void setSpouseMiddleName(String spouseMiddleName) {
        this.spouseMiddleName = spouseMiddleName;
    }

    public String getSpouseLastName() {
        return spouseLastName;
    }

    public void setSpouseLastName(String spouseLastName) {
        this.spouseLastName = spouseLastName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherMiddleName() {
        return motherMiddleName;
    }

    public void setMotherMiddleName(String motherMiddleName) {
        this.motherMiddleName = motherMiddleName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public Long getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(Long aadharNo) {
        this.aadharNo = aadharNo;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public Caste getCaste() {
        return caste;
    }

    public void setCaste(Caste caste) {
        this.caste = caste;
    }

    public String getSubCaste() {
        return subCaste;
    }

    public void setSubCaste(String subCaste) {
        this.subCaste = subCaste;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public Bloodgroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(Bloodgroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getAddressLineThree() {
        return addressLineThree;
    }

    public void setAddressLineThree(String addressLineThree) {
        this.addressLineThree = addressLineThree;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getTeacherContactNumber() {
        return teacherContactNumber;
    }

    public void setTeacherContactNumber(String teacherContactNumber) {
        this.teacherContactNumber = teacherContactNumber;
    }

    public String getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(String alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getTeacherEmailAddress() {
        return teacherEmailAddress;
    }

    public void setTeacherEmailAddress(String teacherEmailAddress) {
        this.teacherEmailAddress = teacherEmailAddress;
    }

    public String getAlternateEmailAddress() {
        return alternateEmailAddress;
    }

    public void setAlternateEmailAddress(String alternateEmailAddress) {
        this.alternateEmailAddress = alternateEmailAddress;
    }

    public RelationWithStudentEnum getRelationWithStaff() {
        return relationWithStaff;
    }

    public void setRelationWithStaff(RelationWithStudentEnum relationWithStaff) {
        this.relationWithStaff = relationWithStaff;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactMiddleName() {
        return emergencyContactMiddleName;
    }

    public void setEmergencyContactMiddleName(String emergencyContactMiddleName) {
        this.emergencyContactMiddleName = emergencyContactMiddleName;
    }

    public String getEmergencyContactLastName() {
        return emergencyContactLastName;
    }

    public void setEmergencyContactLastName(String emergencyContactLastName) {
        this.emergencyContactLastName = emergencyContactLastName;
    }

    public String getEmergencyContactNo() {
        return emergencyContactNo;
    }

    public void setEmergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

    public String getEmergencyContactEmailAddress() {
        return emergencyContactEmailAddress;
    }

    public void setEmergencyContactEmailAddress(String emergencyContactEmailAddress) {
        this.emergencyContactEmailAddress = emergencyContactEmailAddress;
    }

    public String getUploadPhoto() {
        return uploadPhoto;
    }

    public void setUploadPhoto(String uploadPhoto) {
        this.uploadPhoto = uploadPhoto;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aadharNo == null) ? 0 : aadharNo.hashCode());
		result = prime * result + ((addressLineOne == null) ? 0 : addressLineOne.hashCode());
		result = prime * result + ((addressLineThree == null) ? 0 : addressLineThree.hashCode());
		result = prime * result + ((addressLineTwo == null) ? 0 : addressLineTwo.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((alternateContactNumber == null) ? 0 : alternateContactNumber.hashCode());
		result = prime * result + ((alternateEmailAddress == null) ? 0 : alternateEmailAddress.hashCode());
		result = prime * result + ((bloodGroup == null) ? 0 : bloodGroup.hashCode());
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((caste == null) ? 0 : caste.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((departmentId == null) ? 0 : departmentId.hashCode());
		result = prime * result + ((designation == null) ? 0 : designation.hashCode());
		result = prime * result
				+ ((emergencyContactEmailAddress == null) ? 0 : emergencyContactEmailAddress.hashCode());
		result = prime * result + ((emergencyContactLastName == null) ? 0 : emergencyContactLastName.hashCode());
		result = prime * result + ((emergencyContactMiddleName == null) ? 0 : emergencyContactMiddleName.hashCode());
		result = prime * result + ((emergencyContactName == null) ? 0 : emergencyContactName.hashCode());
		result = prime * result + ((emergencyContactNo == null) ? 0 : emergencyContactNo.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((fatherLastName == null) ? 0 : fatherLastName.hashCode());
		result = prime * result + ((fatherMiddleName == null) ? 0 : fatherMiddleName.hashCode());
		result = prime * result + ((fatherName == null) ? 0 : fatherName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motherLastName == null) ? 0 : motherLastName.hashCode());
		result = prime * result + ((motherMiddleName == null) ? 0 : motherMiddleName.hashCode());
		result = prime * result + ((motherName == null) ? 0 : motherName.hashCode());
		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
		result = prime * result + ((placeOfBirth == null) ? 0 : placeOfBirth.hashCode());
		result = prime * result + ((relationWithStaff == null) ? 0 : relationWithStaff.hashCode());
		result = prime * result + ((religion == null) ? 0 : religion.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((spouseLastName == null) ? 0 : spouseLastName.hashCode());
		result = prime * result + ((spouseMiddleName == null) ? 0 : spouseMiddleName.hashCode());
		result = prime * result + ((spouseName == null) ? 0 : spouseName.hashCode());
		result = prime * result + ((staffType == null) ? 0 : staffType.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((subCaste == null) ? 0 : subCaste.hashCode());
		result = prime * result + ((teacherContactNumber == null) ? 0 : teacherContactNumber.hashCode());
		result = prime * result + ((teacherEmailAddress == null) ? 0 : teacherEmailAddress.hashCode());
		result = prime * result + ((teacherLastName == null) ? 0 : teacherLastName.hashCode());
		result = prime * result + ((teacherMiddleName == null) ? 0 : teacherMiddleName.hashCode());
		result = prime * result + ((teacherName == null) ? 0 : teacherName.hashCode());
		result = prime * result + ((town == null) ? 0 : town.hashCode());
		result = prime * result + ((uploadPhoto == null) ? 0 : uploadPhoto.hashCode());
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
		CmsTeacherVo other = (CmsTeacherVo) obj;
		if (aadharNo == null) {
			if (other.aadharNo != null)
				return false;
		} else if (!aadharNo.equals(other.aadharNo))
			return false;
		if (addressLineOne == null) {
			if (other.addressLineOne != null)
				return false;
		} else if (!addressLineOne.equals(other.addressLineOne))
			return false;
		if (addressLineThree == null) {
			if (other.addressLineThree != null)
				return false;
		} else if (!addressLineThree.equals(other.addressLineThree))
			return false;
		if (addressLineTwo == null) {
			if (other.addressLineTwo != null)
				return false;
		} else if (!addressLineTwo.equals(other.addressLineTwo))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (alternateContactNumber == null) {
			if (other.alternateContactNumber != null)
				return false;
		} else if (!alternateContactNumber.equals(other.alternateContactNumber))
			return false;
		if (alternateEmailAddress == null) {
			if (other.alternateEmailAddress != null)
				return false;
		} else if (!alternateEmailAddress.equals(other.alternateEmailAddress))
			return false;
		if (bloodGroup != other.bloodGroup)
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (caste != other.caste)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (departmentId == null) {
			if (other.departmentId != null)
				return false;
		} else if (!departmentId.equals(other.departmentId))
			return false;
		if (designation == null) {
			if (other.designation != null)
				return false;
		} else if (!designation.equals(other.designation))
			return false;
		if (emergencyContactEmailAddress == null) {
			if (other.emergencyContactEmailAddress != null)
				return false;
		} else if (!emergencyContactEmailAddress.equals(other.emergencyContactEmailAddress))
			return false;
		if (emergencyContactLastName == null) {
			if (other.emergencyContactLastName != null)
				return false;
		} else if (!emergencyContactLastName.equals(other.emergencyContactLastName))
			return false;
		if (emergencyContactMiddleName == null) {
			if (other.emergencyContactMiddleName != null)
				return false;
		} else if (!emergencyContactMiddleName.equals(other.emergencyContactMiddleName))
			return false;
		if (emergencyContactName == null) {
			if (other.emergencyContactName != null)
				return false;
		} else if (!emergencyContactName.equals(other.emergencyContactName))
			return false;
		if (emergencyContactNo == null) {
			if (other.emergencyContactNo != null)
				return false;
		} else if (!emergencyContactNo.equals(other.emergencyContactNo))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (fatherLastName == null) {
			if (other.fatherLastName != null)
				return false;
		} else if (!fatherLastName.equals(other.fatherLastName))
			return false;
		if (fatherMiddleName == null) {
			if (other.fatherMiddleName != null)
				return false;
		} else if (!fatherMiddleName.equals(other.fatherMiddleName))
			return false;
		if (fatherName == null) {
			if (other.fatherName != null)
				return false;
		} else if (!fatherName.equals(other.fatherName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motherLastName == null) {
			if (other.motherLastName != null)
				return false;
		} else if (!motherLastName.equals(other.motherLastName))
			return false;
		if (motherMiddleName == null) {
			if (other.motherMiddleName != null)
				return false;
		} else if (!motherMiddleName.equals(other.motherMiddleName))
			return false;
		if (motherName == null) {
			if (other.motherName != null)
				return false;
		} else if (!motherName.equals(other.motherName))
			return false;
		if (pincode == null) {
			if (other.pincode != null)
				return false;
		} else if (!pincode.equals(other.pincode))
			return false;
		if (placeOfBirth == null) {
			if (other.placeOfBirth != null)
				return false;
		} else if (!placeOfBirth.equals(other.placeOfBirth))
			return false;
		if (relationWithStaff != other.relationWithStaff)
			return false;
		if (religion != other.religion)
			return false;
		if (sex != other.sex)
			return false;
		if (spouseLastName == null) {
			if (other.spouseLastName != null)
				return false;
		} else if (!spouseLastName.equals(other.spouseLastName))
			return false;
		if (spouseMiddleName == null) {
			if (other.spouseMiddleName != null)
				return false;
		} else if (!spouseMiddleName.equals(other.spouseMiddleName))
			return false;
		if (spouseName == null) {
			if (other.spouseName != null)
				return false;
		} else if (!spouseName.equals(other.spouseName))
			return false;
		if (staffType != other.staffType)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (status != other.status)
			return false;
		if (subCaste == null) {
			if (other.subCaste != null)
				return false;
		} else if (!subCaste.equals(other.subCaste))
			return false;
		if (teacherContactNumber == null) {
			if (other.teacherContactNumber != null)
				return false;
		} else if (!teacherContactNumber.equals(other.teacherContactNumber))
			return false;
		if (teacherEmailAddress == null) {
			if (other.teacherEmailAddress != null)
				return false;
		} else if (!teacherEmailAddress.equals(other.teacherEmailAddress))
			return false;
		if (teacherLastName == null) {
			if (other.teacherLastName != null)
				return false;
		} else if (!teacherLastName.equals(other.teacherLastName))
			return false;
		if (teacherMiddleName == null) {
			if (other.teacherMiddleName != null)
				return false;
		} else if (!teacherMiddleName.equals(other.teacherMiddleName))
			return false;
		if (teacherName == null) {
			if (other.teacherName != null)
				return false;
		} else if (!teacherName.equals(other.teacherName))
			return false;
		if (town == null) {
			if (other.town != null)
				return false;
		} else if (!town.equals(other.town))
			return false;
		if (uploadPhoto == null) {
			if (other.uploadPhoto != null)
				return false;
		} else if (!uploadPhoto.equals(other.uploadPhoto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CmsTeacherVo [id=" + id + ", teacherName=" + teacherName + ", teacherMiddleName=" + teacherMiddleName
				+ ", teacherLastName=" + teacherLastName + ", fatherName=" + fatherName + ", fatherMiddleName="
				+ fatherMiddleName + ", fatherLastName=" + fatherLastName + ", spouseName=" + spouseName
				+ ", spouseMiddleName=" + spouseMiddleName + ", spouseLastName=" + spouseLastName + ", motherName="
				+ motherName + ", motherMiddleName=" + motherMiddleName + ", motherLastName=" + motherLastName
				+ ", aadharNo=" + aadharNo + ", dateOfBirth=" + dateOfBirth + ", placeOfBirth=" + placeOfBirth
				+ ", religion=" + religion + ", caste=" + caste + ", subCaste=" + subCaste + ", age=" + age + ", sex="
				+ sex + ", bloodGroup=" + bloodGroup + ", addressLineOne=" + addressLineOne + ", addressLineTwo="
				+ addressLineTwo + ", addressLineThree=" + addressLineThree + ", town=" + town + ", state=" + state
				+ ", country=" + country + ", pincode=" + pincode + ", teacherContactNumber=" + teacherContactNumber
				+ ", alternateContactNumber=" + alternateContactNumber + ", teacherEmailAddress=" + teacherEmailAddress
				+ ", alternateEmailAddress=" + alternateEmailAddress + ", relationWithStaff=" + relationWithStaff
				+ ", emergencyContactName=" + emergencyContactName + ", emergencyContactMiddleName="
				+ emergencyContactMiddleName + ", emergencyContactLastName=" + emergencyContactLastName
				+ ", emergencyContactNo=" + emergencyContactNo + ", emergencyContactEmailAddress="
				+ emergencyContactEmailAddress + ", uploadPhoto=" + uploadPhoto + ", status=" + status + ", employeeId="
				+ employeeId + ", designation=" + designation + ", staffType=" + staffType + ", departmentId="
				+ departmentId + ", branchId=" + branchId + ", department=" + department + ", branch=" + branch + "]";
	}

  
}
