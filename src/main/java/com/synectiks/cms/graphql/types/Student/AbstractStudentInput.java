package com.synectiks.cms.graphql.types.Student;

import com.synectiks.cms.domain.enumeration.*;

import java.util.Date;
import java.util.Objects;

public class AbstractStudentInput {
    private Long id;
    private String studentName;
    private String studentMiddleName;
    private String studentLastName;
    private String fatherName;
    private String fatherMiddleName;
    private String fatherLastName;
    private String motherName;
    private String motherMiddleName;
    private String motherLastName;
    private Long aadharNo;
    private Date dateOfBirth;
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
    private String studentContactNumber;
    private String alternateContactNumber;
    private String studentEmailAddress;
    private String alternateEmailAddress;
    private RelationWithStudentEnum relationWithStudent;
    private String emergencyContactName ;
    private String emergencyContactMiddleName ;
    private String emergencyContactLastName ;
    private String emergencyContactNo ;
    private String emergencyContactEmailAddress ;
    private String uploadPhoto;
    private Long admissionNo;
    private String rollNo;
    private StudentTypeEnum studentType;
    private String fileName;
    private Long academicYearId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

    public String getStudentContactNumber() {
        return studentContactNumber;
    }

    public void setStudentContactNumber(String studentContactNumber) {
        this.studentContactNumber = studentContactNumber;
    }

    public String getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(String alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getStudentEmailAddress() {
        return studentEmailAddress;
    }

    public void setStudentEmailAddress(String studentEmailAddress) {
        this.studentEmailAddress = studentEmailAddress;
    }

    public String getAlternateEmailAddress() {
        return alternateEmailAddress;
    }

    public void setAlternateEmailAddress(String alternateEmailAddress) {
        this.alternateEmailAddress = alternateEmailAddress;
    }

    public RelationWithStudentEnum getRelationWithStudent() {
        return relationWithStudent;
    }

    public void setRelationWithStudent(RelationWithStudentEnum relationWithStudent) {
        this.relationWithStudent = relationWithStudent;
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

    public Long getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(Long admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public StudentTypeEnum getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentTypeEnum studentType) {
        this.studentType = studentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractStudentInput that = (AbstractStudentInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(studentName, that.studentName) &&
            Objects.equals(studentMiddleName, that.studentMiddleName) &&
            Objects.equals(studentLastName, that.studentLastName) &&
            Objects.equals(fatherName, that.fatherName) &&
            Objects.equals(fatherMiddleName, that.fatherMiddleName) &&
            Objects.equals(fatherLastName, that.fatherLastName) &&
            Objects.equals(motherName, that.motherName) &&
            Objects.equals(motherMiddleName, that.motherMiddleName) &&
            Objects.equals(motherLastName, that.motherLastName) &&
            Objects.equals(aadharNo, that.aadharNo) &&
            Objects.equals(dateOfBirth, that.dateOfBirth) &&
            Objects.equals(placeOfBirth, that.placeOfBirth) &&
            religion == that.religion &&
            caste == that.caste &&
            Objects.equals(subCaste, that.subCaste) &&
            Objects.equals(age, that.age) &&
            sex == that.sex &&
            bloodGroup == that.bloodGroup &&
            Objects.equals(addressLineOne, that.addressLineOne) &&
            Objects.equals(addressLineTwo, that.addressLineTwo) &&
            Objects.equals(addressLineThree, that.addressLineThree) &&
            Objects.equals(town, that.town) &&
            Objects.equals(state, that.state) &&
            Objects.equals(country, that.country) &&
            Objects.equals(pincode, that.pincode) &&
            Objects.equals(studentContactNumber, that.studentContactNumber) &&
            Objects.equals(alternateContactNumber, that.alternateContactNumber) &&
            Objects.equals(studentEmailAddress, that.studentEmailAddress) &&
            Objects.equals(alternateEmailAddress, that.alternateEmailAddress) &&
            relationWithStudent == that.relationWithStudent &&
            Objects.equals(emergencyContactName, that.emergencyContactName) &&
            Objects.equals(emergencyContactMiddleName, that.emergencyContactMiddleName) &&
            Objects.equals(emergencyContactLastName, that.emergencyContactLastName) &&
            Objects.equals(emergencyContactNo, that.emergencyContactNo) &&
            Objects.equals(emergencyContactEmailAddress, that.emergencyContactEmailAddress) &&
//            Objects.equals(uploadPhoto, that.uploadPhoto) &&
            Objects.equals(admissionNo, that.admissionNo) &&
            Objects.equals(rollNo, that.rollNo) &&
            studentType == that.studentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentName, studentMiddleName, studentLastName, fatherName, fatherMiddleName, fatherLastName, motherName, motherMiddleName, motherLastName, aadharNo, dateOfBirth, placeOfBirth, religion, caste, subCaste, age, sex, bloodGroup, addressLineOne, addressLineTwo, addressLineThree, town, state, country, pincode, studentContactNumber, alternateContactNumber, studentEmailAddress, alternateEmailAddress, relationWithStudent, emergencyContactName, emergencyContactMiddleName, emergencyContactLastName, emergencyContactNo, emergencyContactEmailAddress, admissionNo, rollNo, studentType);
    }

    @Override
    public String toString() {
        return "AbstractStudentInput{" +
            "id=" + id +
            ", studentName='" + studentName + '\'' +
            ", studentMiddleName='" + studentMiddleName + '\'' +
            ", studentLastName='" + studentLastName + '\'' +
            ", fatherName='" + fatherName + '\'' +
            ", fatherMiddleName='" + fatherMiddleName + '\'' +
            ", fatherLastName='" + fatherLastName + '\'' +
            ", motherName='" + motherName + '\'' +
            ", motherMiddleName='" + motherMiddleName + '\'' +
            ", motherLastName='" + motherLastName + '\'' +
            ", aadharNo=" + aadharNo +
            ", dateOfBirth=" + dateOfBirth +
            ", placeOfBirth='" + placeOfBirth + '\'' +
            ", religion=" + religion +
            ", caste=" + caste +
            ", subCaste='" + subCaste + '\'' +
            ", age=" + age +
            ", sex=" + sex +
            ", bloodGroup=" + bloodGroup +
            ", addressLineOne='" + addressLineOne + '\'' +
            ", addressLineTwo='" + addressLineTwo + '\'' +
            ", addressLineThree='" + addressLineThree + '\'' +
            ", town='" + town + '\'' +
            ", state='" + state + '\'' +
            ", country='" + country + '\'' +
            ", pincode=" + pincode +
            ", studentContactNumber='" + studentContactNumber + '\'' +
            ", alternateContactNumber='" + alternateContactNumber + '\'' +
            ", studentEmailAddress='" + studentEmailAddress + '\'' +
            ", alternateEmailAddress='" + alternateEmailAddress + '\'' +
            ", relationWithStudent=" + relationWithStudent +
            ", emergencyContactName='" + emergencyContactName + '\'' +
            ", emergencyContactMiddleName='" + emergencyContactMiddleName + '\'' +
            ", emergencyContactLastName='" + emergencyContactLastName + '\'' +
            ", emergencyContactNo='" + emergencyContactNo + '\'' +
            ", emergencyContactEmailAddress='" + emergencyContactEmailAddress + '\'' +
//            ", uploadPhoto=" + uploadPhoto +
            ", admissionNo=" + admissionNo +
            ", rollNo='" + rollNo + '\'' +
            ", studentType=" + studentType +
            '}';
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(Long academicYearId) {
		this.academicYearId = academicYearId;
	}
}
