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
    private Long studentContactNumber;
    private Long alternateContactNumber;
    private String studentEmailAddress;
    private String alternateEmailAddress;
    private RelationWithStudentEnum relationWithStudent;
    private String name;
    private String middleName;
    private String lastName;
    private Long contactNo;
    private String emailAddress;
    private Status transport;
    private Status mess;
    private Status gym;
    private Status culturalClass;
    private Status library;
    private Status sports;
    private Status swimming;
    private Status extraClass;
    private Status handicrafts;
    private Status add;
    private Long uploadPhoto;
    private Long admissionNo;
    private String rollNo;
    private StudentTypeEnum studentType;


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

    public RelationWithStudentEnum getRelationWithStudent() {
        return relationWithStudent;
    }

    public void setRelationWithStudent(RelationWithStudentEnum relationWithStudent) {
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

    public Status getTransport() {
        return transport;
    }

    public void setTransport(Status transport) {
        this.transport = transport;
    }

    public Status getMess() {
        return mess;
    }

    public void setMess(Status mess) {
        this.mess = mess;
    }

    public Status getGym() {
        return gym;
    }

    public void setGym(Status gym) {
        this.gym = gym;
    }

    public Status getCulturalClass() {
        return culturalClass;
    }

    public void setCulturalClass(Status culturalClass) {
        this.culturalClass = culturalClass;
    }

    public Status getLibrary() {
        return library;
    }

    public void setLibrary(Status library) {
        this.library = library;
    }

    public Status getSports() {
        return sports;
    }

    public void setSports(Status sports) {
        this.sports = sports;
    }

    public Status getSwimming() {
        return swimming;
    }

    public void setSwimming(Status swimming) {
        this.swimming = swimming;
    }

    public Status getExtraClass() {
        return extraClass;
    }

    public void setExtraClass(Status extraClass) {
        this.extraClass = extraClass;
    }

    public Status getHandicrafts() {
        return handicrafts;
    }

    public void setHandicrafts(Status handicrafts) {
        this.handicrafts = handicrafts;
    }

    public Status getAdd() {
        return add;
    }

    public void setAdd(Status add) {
        this.add = add;
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

    public StudentTypeEnum getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentTypeEnum studentType) {
        this.studentType = studentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractStudentInput)) return false;
        AbstractStudentInput that = (AbstractStudentInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getStudentName(), that.getStudentName()) &&
            Objects.equals(getStudentMiddleName(), that.getStudentMiddleName()) &&
            Objects.equals(getStudentLastName(), that.getStudentLastName()) &&
            Objects.equals(getFatherName(), that.getFatherName()) &&
            Objects.equals(getFatherMiddleName(), that.getFatherMiddleName()) &&
            Objects.equals(getFatherLastName(), that.getFatherLastName()) &&
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
            Objects.equals(getStudentContactNumber(), that.getStudentContactNumber()) &&
            Objects.equals(getAlternateContactNumber(), that.getAlternateContactNumber()) &&
            Objects.equals(getStudentEmailAddress(), that.getStudentEmailAddress()) &&
            Objects.equals(getAlternateEmailAddress(), that.getAlternateEmailAddress()) &&
            getRelationWithStudent() == that.getRelationWithStudent() &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getMiddleName(), that.getMiddleName()) &&
            Objects.equals(getLastName(), that.getLastName()) &&
            Objects.equals(getContactNo(), that.getContactNo()) &&
            Objects.equals(getEmailAddress(), that.getEmailAddress()) &&
            getTransport() == that.getTransport() &&
            getMess() == that.getMess() &&
            getGym() == that.getGym() &&
            getCulturalClass() == that.getCulturalClass() &&
            getLibrary() == that.getLibrary() &&
            getSports() == that.getSports() &&
            getSwimming() == that.getSwimming() &&
            getExtraClass() == that.getExtraClass() &&
            getHandicrafts() == that.getHandicrafts() &&
            getAdd() == that.getAdd() &&
            Objects.equals(getUploadPhoto(), that.getUploadPhoto()) &&
            Objects.equals(getAdmissionNo(), that.getAdmissionNo()) &&
            Objects.equals(getRollNo(), that.getRollNo()) &&
            getStudentType() == that.getStudentType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStudentName(), getStudentMiddleName(), getStudentLastName(), getFatherName(), getFatherMiddleName(), getFatherLastName(), getMotherName(), getMotherMiddleName(), getMotherLastName(), getAadharNo(), getDateOfBirth(), getPlaceOfBirth(), getReligion(), getCaste(), getSubCaste(), getAge(), getSex(), getBloodGroup(), getAddressLineOne(), getAddressLineTwo(), getAddressLineThree(), getTown(), getState(), getCountry(), getPincode(), getStudentContactNumber(), getAlternateContactNumber(), getStudentEmailAddress(), getAlternateEmailAddress(), getRelationWithStudent(), getName(), getMiddleName(), getLastName(), getContactNo(), getEmailAddress(), getTransport(), getMess(), getGym(), getCulturalClass(), getLibrary(), getSports(), getSwimming(), getExtraClass(), getHandicrafts(), getAdd(), getUploadPhoto(), getAdmissionNo(), getRollNo(), getStudentType());
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
            ", studentContactNumber=" + studentContactNumber +
            ", alternateContactNumber=" + alternateContactNumber +
            ", studentEmailAddress='" + studentEmailAddress + '\'' +
            ", alternateEmailAddress='" + alternateEmailAddress + '\'' +
            ", relationWithStudent=" + relationWithStudent +
            ", name='" + name + '\'' +
            ", middleName='" + middleName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", contactNo=" + contactNo +
            ", emailAddress='" + emailAddress + '\'' +
            ", transport=" + transport +
            ", mess=" + mess +
            ", gym=" + gym +
            ", culturalClass=" + culturalClass +
            ", library=" + library +
            ", sports=" + sports +
            ", swimming=" + swimming +
            ", extraClass=" + extraClass +
            ", handicrafts=" + handicrafts +
            ", add=" + add +
            ", uploadPhoto=" + uploadPhoto +
            ", admissionNo=" + admissionNo +
            ", rollNo='" + rollNo + '\'' +
            ", studentType=" + studentType +
            '}';
    }
}
