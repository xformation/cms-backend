package com.synectiks.cms.graphql.types.Teacher;

import com.synectiks.commons.entities.cms.enumeration.*;

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
    private Status status;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractTeacherInput)) return false;
        AbstractTeacherInput that = (AbstractTeacherInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getTeacherName(), that.getTeacherName()) &&
            Objects.equals(getTeacherMiddleName(), that.getTeacherMiddleName()) &&
            Objects.equals(getTeacherLastName(), that.getTeacherLastName()) &&
            Objects.equals(getFatherName(), that.getFatherName()) &&
            Objects.equals(getFatherMiddleName(), that.getFatherMiddleName()) &&
            Objects.equals(getFatherLastName(), that.getFatherLastName()) &&
            Objects.equals(getSpouseName(), that.getSpouseName()) &&
            Objects.equals(getSpouseMiddleName(), that.getSpouseMiddleName()) &&
            Objects.equals(getSpouseLastName(), that.getSpouseLastName()) &&
            Objects.equals(getMotherName(), that.getMotherName()) &&
            Objects.equals(getMotherMiddleName(), that.getMotherMiddleName()) &&
            Objects.equals(getMotherLastName(), that.getMotherLastName()) &&
            Objects.equals(getAadharNo(), that.getAadharNo()) &&
            Objects.equals(getDateOfBirth(), that.getDateOfBirth()) &&
            Objects.equals(getPlaceOfBirth(), that.getPlaceOfBirth()) &&
            getReligion() == that.getReligion() &&
            getCaste() == that.getCaste() &&
            Objects.equals(getSubCaste(), that.getSubCaste()) &&
            Objects.equals(getAge(), that.getAge()) &&
            getSex() == that.getSex() &&
            getBloodGroup() == that.getBloodGroup() &&
            Objects.equals(getAddressLineOne(), that.getAddressLineOne()) &&
            Objects.equals(getAddressLineTwo(), that.getAddressLineTwo()) &&
            Objects.equals(getAddressLineThree(), that.getAddressLineThree()) &&
            Objects.equals(getTown(), that.getTown()) &&
            Objects.equals(getState(), that.getState()) &&
            Objects.equals(getCountry(), that.getCountry()) &&
            Objects.equals(getPincode(), that.getPincode()) &&
            Objects.equals(getTeacherContactNumber(), that.getTeacherContactNumber()) &&
            Objects.equals(getAlternateContactNumber(), that.getAlternateContactNumber()) &&
            Objects.equals(getTeacherEmailAddress(), that.getTeacherEmailAddress()) &&
            Objects.equals(getAlternateEmailAddress(), that.getAlternateEmailAddress()) &&
            getRelationWithStaff() == that.getRelationWithStaff() &&
            Objects.equals(getEmergencyContactName(), that.getEmergencyContactName()) &&
            Objects.equals(getEmergencyContactMiddleName(), that.getEmergencyContactMiddleName()) &&
            Objects.equals(getEmergencyContactLastName(), that.getEmergencyContactLastName()) &&
            Objects.equals(getEmergencyContactNo(), that.getEmergencyContactNo()) &&
            Objects.equals(getEmergencyContactEmailAddress(), that.getEmergencyContactEmailAddress()) &&
            Objects.equals(getUploadPhoto(), that.getUploadPhoto()) &&
            Objects.equals(getEmployeeId(), that.getEmployeeId()) &&
            Objects.equals(getDesignation(), that.getDesignation()) &&
            getStaffType() == that.getStaffType() &&
            getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTeacherName(), getTeacherMiddleName(), getTeacherLastName(), getFatherName(), getFatherMiddleName(), getFatherLastName(), getSpouseName(), getSpouseMiddleName(), getSpouseLastName(), getMotherName(), getMotherMiddleName(), getMotherLastName(), getAadharNo(), getDateOfBirth(), getPlaceOfBirth(), getReligion(), getCaste(), getSubCaste(), getAge(), getSex(), getBloodGroup(), getAddressLineOne(), getAddressLineTwo(), getAddressLineThree(), getTown(), getState(), getCountry(), getPincode(), getTeacherContactNumber(), getAlternateContactNumber(), getTeacherEmailAddress(), getAlternateEmailAddress(), getRelationWithStaff(), getEmergencyContactName(), getEmergencyContactMiddleName(), getEmergencyContactLastName(), getEmergencyContactNo(), getEmergencyContactEmailAddress(), getUploadPhoto(), getEmployeeId(), getDesignation(), getStaffType(), getStatus());
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
            ", status=" + status +
            '}';
    }
}

