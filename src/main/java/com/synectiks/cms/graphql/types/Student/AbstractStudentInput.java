package com.synectiks.cms.graphql.types.Student;

import com.synectiks.cms.domain.enumeration.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

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
    private String religion;
    private String caste;
    private String subCaste;
    private Integer age;
    private Gender sex;
    private String bloodGroup;
    private String addressLineOne;
    private String addressLineTwo;
    private String addressLineThree;
    private String town;
    private String state;
    private String country;
    private Long pincode;
    private Long studentContactNumber;
    private Long alternateContactNumber;
    private String studentEmailAddress;
    private String alternateEmailAddress;
    private String relationWithStudent;
    private String name;
    private String middleName;
    private String lastName;
    private Long contactNo;
    private String emailAddress;
    private Long uploadPhoto;
    private Long admissionNo;
    private String rollNo;
    private String studentType;

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

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
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

    public Long getStudentContactNumber() {
        return studentContactNumber;
    }

    public void setStudentContactNumber(Long studentContactNumber) {
        this.studentContactNumber = studentContactNumber;
    }

    public Long getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(Long alternateContactNumber) {
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

    public String getRelationWithStudent() {
        return relationWithStudent;
    }

    public void setRelationWithStudent(String relationWithStudent) {
        this.relationWithStudent = relationWithStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getContactNo() {
        return contactNo;
    }

    public void setContactNo(Long contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getUploadPhoto() {
        return uploadPhoto;
    }

    public void setUploadPhoto(Long uploadPhoto) {
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

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
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
            ", religion='" + religion + '\'' +
            ", caste='" + caste + '\'' +
            ", subCaste='" + subCaste + '\'' +
            ", age=" + age +
            ", sex=" + sex +
            ", bloodGroup='" + bloodGroup + '\'' +
            ", addressLineOne='" + addressLineOne + '\'' +
            ", addressLineTwo='" + addressLineTwo + '\'' +
            ", addressLineThree='" + addressLineThree + '\'' +
            ", town='" + town + '\'' +
            ", state='" + state + '\'' +
            ", country='" + country + '\'' +
            ", pincode=" + pincode +
            ", studentContactNumber=" + studentContactNumber +
            ", alternateContactNumber=" + alternateContactNumber +
            ", studentEmailAddress='" + studentEmailAddress + '\'' +
            ", alternateEmailAddress='" + alternateEmailAddress + '\'' +
            ", relationWithStudent='" + relationWithStudent + '\'' +
            ", name='" + name + '\'' +
            ", middleName='" + middleName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", contactNo=" + contactNo +
            ", emailAddress='" + emailAddress + '\'' +
            ", uploadPhoto=" + uploadPhoto +
            ", admissionNo=" + admissionNo +
            ", rollNo='" + rollNo + '\'' +
            ", studentType='" + studentType + '\'' +
            '}';
    }
}
