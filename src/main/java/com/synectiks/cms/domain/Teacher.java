package com.synectiks.cms.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.Religion;

import com.synectiks.cms.domain.enumeration.Caste;

import com.synectiks.cms.domain.enumeration.Gender;

import com.synectiks.cms.domain.enumeration.Bloodgroup;

import com.synectiks.cms.domain.enumeration.RelationWithStudentEnum;

import com.synectiks.cms.domain.enumeration.StaffType;

/**
 * A Teacher.
 */
@Entity
@Table(name = "teacher")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "teacher_name", nullable = false)
    private String teacherName;

    @NotNull
    @Column(name = "teacher_middle_name", nullable = false)
    private String teacherMiddleName;

    @NotNull
    @Column(name = "teacher_last_name", nullable = false)
    private String teacherLastName;

    @NotNull
    @Column(name = "father_name", nullable = false)
    private String fatherName;

    @NotNull
    @Column(name = "father_middle_name", nullable = false)
    private String fatherMiddleName;

    @NotNull
    @Column(name = "father_last_name", nullable = false)
    private String fatherLastName;

    @NotNull
    @Column(name = "mother_name", nullable = false)
    private String motherName;

    @NotNull
    @Column(name = "mother_middle_name", nullable = false)
    private String motherMiddleName;

    @NotNull
    @Column(name = "mother_last_name", nullable = false)
    private String motherLastName;

    @NotNull
    @Column(name = "aadhar_no", nullable = false)
    private Long aadharNo;

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @NotNull
    @Column(name = "place_of_birth", nullable = false)
    private String placeOfBirth;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "religion", nullable = false)
    private Religion religion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "caste", nullable = false)
    private Caste caste;

    @NotNull
    @Column(name = "sub_caste", nullable = false)
    private String subCaste;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Gender sex;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group", nullable = false)
    private Bloodgroup bloodGroup;

    @NotNull
    @Column(name = "address_line_one", nullable = false)
    private String addressLineOne;

    @NotNull
    @Column(name = "address_line_two", nullable = false)
    private String addressLineTwo;

    @NotNull
    @Column(name = "address_line_three", nullable = false)
    private String addressLineThree;

    @NotNull
    @Column(name = "town", nullable = false)
    private String town;

    @NotNull
    @Column(name = "state", nullable = false)
    private String state;

    @NotNull
    @Column(name = "country", nullable = false)
    private String country;

    @NotNull
    @Column(name = "pincode", nullable = false)
    private Long pincode;

    @NotNull
    @Column(name = "teacher_contact_number", nullable = false)
    private Long teacherContactNumber;

    @NotNull
    @Column(name = "alternate_contact_number", nullable = false)
    private Long alternateContactNumber;

    @NotNull
    @Column(name = "teacher_email_address", nullable = false)
    private String teacherEmailAddress;

    @NotNull
    @Column(name = "alternate_email_address", nullable = false)
    private String alternateEmailAddress;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "relation_with_staff", nullable = false)
    private RelationWithStudentEnum relationWithStaff;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "middle_name", nullable = false)
    private String middleName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "contact_no", nullable = false)
    private Long contactNo;

    @NotNull
    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @NotNull
    @Column(name = "upload_photo", nullable = false)
    private Long uploadPhoto;

    @NotNull
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @NotNull
    @Column(name = "designation", nullable = false)
    private String designation;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "staff_type", nullable = false)
    private StaffType staffType;

    @ManyToOne
    @JsonIgnoreProperties("teachers")
    private Department department;

    @ManyToOne
    @JsonIgnoreProperties("teachers")
    private Branch branch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public Teacher teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherMiddleName() {
        return teacherMiddleName;
    }

    public Teacher teacherMiddleName(String teacherMiddleName) {
        this.teacherMiddleName = teacherMiddleName;
        return this;
    }

    public void setTeacherMiddleName(String teacherMiddleName) {
        this.teacherMiddleName = teacherMiddleName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public Teacher teacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
        return this;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Teacher fatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherMiddleName() {
        return fatherMiddleName;
    }

    public Teacher fatherMiddleName(String fatherMiddleName) {
        this.fatherMiddleName = fatherMiddleName;
        return this;
    }

    public void setFatherMiddleName(String fatherMiddleName) {
        this.fatherMiddleName = fatherMiddleName;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public Teacher fatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
        return this;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public String getMotherName() {
        return motherName;
    }

    public Teacher motherName(String motherName) {
        this.motherName = motherName;
        return this;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherMiddleName() {
        return motherMiddleName;
    }

    public Teacher motherMiddleName(String motherMiddleName) {
        this.motherMiddleName = motherMiddleName;
        return this;
    }

    public void setMotherMiddleName(String motherMiddleName) {
        this.motherMiddleName = motherMiddleName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public Teacher motherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
        return this;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public Long getAadharNo() {
        return aadharNo;
    }

    public Teacher aadharNo(Long aadharNo) {
        this.aadharNo = aadharNo;
        return this;
    }

    public void setAadharNo(Long aadharNo) {
        this.aadharNo = aadharNo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Teacher dateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public Teacher placeOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
        return this;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Religion getReligion() {
        return religion;
    }

    public Teacher religion(Religion religion) {
        this.religion = religion;
        return this;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public Caste getCaste() {
        return caste;
    }

    public Teacher caste(Caste caste) {
        this.caste = caste;
        return this;
    }

    public void setCaste(Caste caste) {
        this.caste = caste;
    }

    public String getSubCaste() {
        return subCaste;
    }

    public Teacher subCaste(String subCaste) {
        this.subCaste = subCaste;
        return this;
    }

    public void setSubCaste(String subCaste) {
        this.subCaste = subCaste;
    }

    public Integer getAge() {
        return age;
    }

    public Teacher age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getSex() {
        return sex;
    }

    public Teacher sex(Gender sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public Bloodgroup getBloodGroup() {
        return bloodGroup;
    }

    public Teacher bloodGroup(Bloodgroup bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(Bloodgroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public Teacher addressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
        return this;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public Teacher addressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
        return this;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getAddressLineThree() {
        return addressLineThree;
    }

    public Teacher addressLineThree(String addressLineThree) {
        this.addressLineThree = addressLineThree;
        return this;
    }

    public void setAddressLineThree(String addressLineThree) {
        this.addressLineThree = addressLineThree;
    }

    public String getTown() {
        return town;
    }

    public Teacher town(String town) {
        this.town = town;
        return this;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public Teacher state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public Teacher country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPincode() {
        return pincode;
    }

    public Teacher pincode(Long pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public Long getTeacherContactNumber() {
        return teacherContactNumber;
    }

    public Teacher teacherContactNumber(Long teacherContactNumber) {
        this.teacherContactNumber = teacherContactNumber;
        return this;
    }

    public void setTeacherContactNumber(Long teacherContactNumber) {
        this.teacherContactNumber = teacherContactNumber;
    }

    public Long getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public Teacher alternateContactNumber(Long alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
        return this;
    }

    public void setAlternateContactNumber(Long alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getTeacherEmailAddress() {
        return teacherEmailAddress;
    }

    public Teacher teacherEmailAddress(String teacherEmailAddress) {
        this.teacherEmailAddress = teacherEmailAddress;
        return this;
    }

    public void setTeacherEmailAddress(String teacherEmailAddress) {
        this.teacherEmailAddress = teacherEmailAddress;
    }

    public String getAlternateEmailAddress() {
        return alternateEmailAddress;
    }

    public Teacher alternateEmailAddress(String alternateEmailAddress) {
        this.alternateEmailAddress = alternateEmailAddress;
        return this;
    }

    public void setAlternateEmailAddress(String alternateEmailAddress) {
        this.alternateEmailAddress = alternateEmailAddress;
    }

    public RelationWithStudentEnum getRelationWithStaff() {
        return relationWithStaff;
    }

    public Teacher relationWithStaff(RelationWithStudentEnum relationWithStaff) {
        this.relationWithStaff = relationWithStaff;
        return this;
    }

    public void setRelationWithStaff(RelationWithStudentEnum relationWithStaff) {
        this.relationWithStaff = relationWithStaff;
    }

    public String getName() {
        return name;
    }

    public Teacher name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Teacher middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Teacher lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getContactNo() {
        return contactNo;
    }

    public Teacher contactNo(Long contactNo) {
        this.contactNo = contactNo;
        return this;
    }

    public void setContactNo(Long contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Teacher emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getUploadPhoto() {
        return uploadPhoto;
    }

    public Teacher uploadPhoto(Long uploadPhoto) {
        this.uploadPhoto = uploadPhoto;
        return this;
    }

    public void setUploadPhoto(Long uploadPhoto) {
        this.uploadPhoto = uploadPhoto;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Teacher employeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getDesignation() {
        return designation;
    }

    public Teacher designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public Teacher staffType(StaffType staffType) {
        this.staffType = staffType;
        return this;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    public Department getDepartment() {
        return department;
    }

    public Teacher department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Branch getBranch() {
        return branch;
    }

    public Teacher branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
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
        Teacher teacher = (Teacher) o;
        if (teacher.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacher.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Teacher{" +
            "id=" + getId() +
            ", teacherName='" + getTeacherName() + "'" +
            ", teacherMiddleName='" + getTeacherMiddleName() + "'" +
            ", teacherLastName='" + getTeacherLastName() + "'" +
            ", fatherName='" + getFatherName() + "'" +
            ", fatherMiddleName='" + getFatherMiddleName() + "'" +
            ", fatherLastName='" + getFatherLastName() + "'" +
            ", motherName='" + getMotherName() + "'" +
            ", motherMiddleName='" + getMotherMiddleName() + "'" +
            ", motherLastName='" + getMotherLastName() + "'" +
            ", aadharNo=" + getAadharNo() +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", placeOfBirth='" + getPlaceOfBirth() + "'" +
            ", religion='" + getReligion() + "'" +
            ", caste='" + getCaste() + "'" +
            ", subCaste='" + getSubCaste() + "'" +
            ", age=" + getAge() +
            ", sex='" + getSex() + "'" +
            ", bloodGroup='" + getBloodGroup() + "'" +
            ", addressLineOne='" + getAddressLineOne() + "'" +
            ", addressLineTwo='" + getAddressLineTwo() + "'" +
            ", addressLineThree='" + getAddressLineThree() + "'" +
            ", town='" + getTown() + "'" +
            ", state='" + getState() + "'" +
            ", country='" + getCountry() + "'" +
            ", pincode=" + getPincode() +
            ", teacherContactNumber=" + getTeacherContactNumber() +
            ", alternateContactNumber=" + getAlternateContactNumber() +
            ", teacherEmailAddress='" + getTeacherEmailAddress() + "'" +
            ", alternateEmailAddress='" + getAlternateEmailAddress() + "'" +
            ", relationWithStaff='" + getRelationWithStaff() + "'" +
            ", name='" + getName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", contactNo=" + getContactNo() +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", uploadPhoto=" + getUploadPhoto() +
            ", employeeId=" + getEmployeeId() +
            ", designation='" + getDesignation() + "'" +
            ", staffType='" + getStaffType() + "'" +
            "}";
    }
}
