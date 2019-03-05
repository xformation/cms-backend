package com.synectiks.cms.graphql.types.Teacher;

import com.synectiks.cms.domain.enumeration.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class AbstractTeacherInput {
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
    private String teacherContactNumber;
    private String alternateContactNumber;
    private String teacherEmailAddress;
    private String alternateEmailAddress;
    private RelationWithStudentEnum relationWithStaff;
    private String emergencyContactName ;
    private String emergencyContactMiddleName ;
    private String emergencyContactLastName ;
    private String emergencyContactNo ;
    private String emergencyContactEmailAddress ;
    private String uploadPhoto;
    private Long employeeId;
    private String designation;
    private StaffType staffType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTeacherInput that = (AbstractTeacherInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(teacherName, that.teacherName) &&
            Objects.equals(teacherMiddleName, that.teacherMiddleName) &&
            Objects.equals(teacherLastName, that.teacherLastName) &&
            Objects.equals(fatherName, that.fatherName) &&
            Objects.equals(fatherMiddleName, that.fatherMiddleName) &&
            Objects.equals(fatherLastName, that.fatherLastName) &&
            Objects.equals(spouseName, that.spouseName) &&
            Objects.equals(spouseMiddleName, that.spouseMiddleName) &&
            Objects.equals(spouseLastName, that.spouseLastName) &&
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
            Objects.equals(teacherContactNumber, that.teacherContactNumber) &&
            Objects.equals(alternateContactNumber, that.alternateContactNumber) &&
            Objects.equals(teacherEmailAddress, that.teacherEmailAddress) &&
            Objects.equals(alternateEmailAddress, that.alternateEmailAddress) &&
            relationWithStaff == that.relationWithStaff &&
            Objects.equals(emergencyContactName, that.emergencyContactName) &&
            Objects.equals(emergencyContactMiddleName, that.emergencyContactMiddleName) &&
            Objects.equals(emergencyContactLastName, that.emergencyContactLastName) &&
            Objects.equals(emergencyContactNo, that.emergencyContactNo) &&
            Objects.equals(emergencyContactEmailAddress, that.emergencyContactEmailAddress) &&
            Objects.equals(uploadPhoto, that.uploadPhoto) &&
            Objects.equals(employeeId, that.employeeId) &&
            Objects.equals(designation, that.designation) &&
            staffType == that.staffType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacherName, teacherMiddleName, teacherLastName, fatherName, fatherMiddleName, fatherLastName, spouseName, spouseMiddleName, spouseLastName, motherName, motherMiddleName, motherLastName, aadharNo, dateOfBirth, placeOfBirth, religion, caste, subCaste, age, sex, bloodGroup, addressLineOne, addressLineTwo, addressLineThree, town, state, country, pincode, teacherContactNumber, alternateContactNumber, teacherEmailAddress, alternateEmailAddress, relationWithStaff, emergencyContactName, emergencyContactMiddleName, emergencyContactLastName, emergencyContactNo, emergencyContactEmailAddress, uploadPhoto, employeeId, designation, staffType);
    }

    @Override
    public String toString() {
        return "AbstractTeacherInput{" +
            "id=" + id +
            ", teacherName='" + teacherName + '\'' +
            ", teacherMiddleName='" + teacherMiddleName + '\'' +
            ", teacherLastName='" + teacherLastName + '\'' +
            ", fatherName='" + fatherName + '\'' +
            ", fatherMiddleName='" + fatherMiddleName + '\'' +
            ", fatherLastName='" + fatherLastName + '\'' +
            ", spouseName='" + spouseName + '\'' +
            ", spouseMiddleName='" + spouseMiddleName + '\'' +
            ", spouseLastName='" + spouseLastName + '\'' +
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
            ", teacherContactNumber='" + teacherContactNumber + '\'' +
            ", alternateContactNumber='" + alternateContactNumber + '\'' +
            ", teacherEmailAddress='" + teacherEmailAddress + '\'' +
            ", alternateEmailAddress='" + alternateEmailAddress + '\'' +
            ", relationWithStaff=" + relationWithStaff +
            ", emergencyContactName='" + emergencyContactName + '\'' +
            ", emergencyContactMiddleName='" + emergencyContactMiddleName + '\'' +
            ", emergencyContactLastName='" + emergencyContactLastName + '\'' +
            ", emergencyContactNo='" + emergencyContactNo + '\'' +
            ", emergencyContactEmailAddress='" + emergencyContactEmailAddress + '\'' +
            ", uploadPhoto='" + uploadPhoto + '\'' +
            ", employeeId=" + employeeId +
            ", designation='" + designation + '\'' +
            ", staffType=" + staffType +
            '}';
    }
}

