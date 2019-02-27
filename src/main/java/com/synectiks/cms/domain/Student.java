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

import com.synectiks.cms.domain.enumeration.Status;

import com.synectiks.cms.domain.enumeration.StudentTypeEnum;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "student_name", nullable = false)
    private String studentName;

    @NotNull
    @Column(name = "student_middle_name", nullable = false)
    private String studentMiddleName;

    @NotNull
    @Column(name = "student_last_name", nullable = false)
    private String studentLastName;

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
    @Column(name = "student_contact_number", nullable = false)
    private Long studentContactNumber;

    @NotNull
    @Column(name = "alternate_contact_number", nullable = false)
    private Long alternateContactNumber;

    @NotNull
    @Column(name = "student_email_address", nullable = false)
    private String studentEmailAddress;

    @NotNull
    @Column(name = "alternate_email_address", nullable = false)
    private String alternateEmailAddress;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "relation_with_student", nullable = false)
    private RelationWithStudentEnum relationWithStudent;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "transport", nullable = false)
    private Status transport;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "mess", nullable = false)
    private Status mess;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gym", nullable = false)
    private Status gym;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "cultural_class", nullable = false)
    private Status culturalClass;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_library", nullable = false)
    private Status library;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sports", nullable = false)
    private Status sports;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "swimming", nullable = false)
    private Status swimming;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "extra_class", nullable = false)
    private Status extraClass;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "handicrafts", nullable = false)
    private Status handicrafts;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_add", nullable = false)
    private Status add;

    @NotNull
    @Column(name = "upload_photo", nullable = false)
    private Long uploadPhoto;

    @NotNull
    @Column(name = "admission_no", nullable = false)
    private Long admissionNo;

    @NotNull
    @Column(name = "roll_no", nullable = false)
    private String rollNo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "student_type", nullable = false)
    private StudentTypeEnum studentType;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("students")
    private Department department;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("students")
    private Batch batch;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("students")
    private Section section;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("students")
    private Branch branch;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public Student studentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public Student studentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
        return this;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public Student studentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
        return this;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Student fatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherMiddleName() {
        return fatherMiddleName;
    }

    public Student fatherMiddleName(String fatherMiddleName) {
        this.fatherMiddleName = fatherMiddleName;
        return this;
    }

    public void setFatherMiddleName(String fatherMiddleName) {
        this.fatherMiddleName = fatherMiddleName;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public Student fatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
        return this;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public String getMotherName() {
        return motherName;
    }

    public Student motherName(String motherName) {
        this.motherName = motherName;
        return this;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherMiddleName() {
        return motherMiddleName;
    }

    public Student motherMiddleName(String motherMiddleName) {
        this.motherMiddleName = motherMiddleName;
        return this;
    }

    public void setMotherMiddleName(String motherMiddleName) {
        this.motherMiddleName = motherMiddleName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public Student motherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
        return this;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public Long getAadharNo() {
        return aadharNo;
    }

    public Student aadharNo(Long aadharNo) {
        this.aadharNo = aadharNo;
        return this;
    }

    public void setAadharNo(Long aadharNo) {
        this.aadharNo = aadharNo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Student dateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public Student placeOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
        return this;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Religion getReligion() {
        return religion;
    }

    public Student religion(Religion religion) {
        this.religion = religion;
        return this;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public Caste getCaste() {
        return caste;
    }

    public Student caste(Caste caste) {
        this.caste = caste;
        return this;
    }

    public void setCaste(Caste caste) {
        this.caste = caste;
    }

    public String getSubCaste() {
        return subCaste;
    }

    public Student subCaste(String subCaste) {
        this.subCaste = subCaste;
        return this;
    }

    public void setSubCaste(String subCaste) {
        this.subCaste = subCaste;
    }

    public Integer getAge() {
        return age;
    }

    public Student age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getSex() {
        return sex;
    }

    public Student sex(Gender sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public Bloodgroup getBloodGroup() {
        return bloodGroup;
    }

    public Student bloodGroup(Bloodgroup bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(Bloodgroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public Student addressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
        return this;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public Student addressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
        return this;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getAddressLineThree() {
        return addressLineThree;
    }

    public Student addressLineThree(String addressLineThree) {
        this.addressLineThree = addressLineThree;
        return this;
    }

    public void setAddressLineThree(String addressLineThree) {
        this.addressLineThree = addressLineThree;
    }

    public String getTown() {
        return town;
    }

    public Student town(String town) {
        this.town = town;
        return this;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public Student state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public Student country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPincode() {
        return pincode;
    }

    public Student pincode(Long pincode) {
        this.pincode = pincode;
        return this;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public Long getStudentContactNumber() {
        return studentContactNumber;
    }

    public Student studentContactNumber(Long studentContactNumber) {
        this.studentContactNumber = studentContactNumber;
        return this;
    }

    public void setStudentContactNumber(Long studentContactNumber) {
        this.studentContactNumber = studentContactNumber;
    }

    public Long getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public Student alternateContactNumber(Long alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
        return this;
    }

    public void setAlternateContactNumber(Long alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getStudentEmailAddress() {
        return studentEmailAddress;
    }

    public Student studentEmailAddress(String studentEmailAddress) {
        this.studentEmailAddress = studentEmailAddress;
        return this;
    }

    public void setStudentEmailAddress(String studentEmailAddress) {
        this.studentEmailAddress = studentEmailAddress;
    }

    public String getAlternateEmailAddress() {
        return alternateEmailAddress;
    }

    public Student alternateEmailAddress(String alternateEmailAddress) {
        this.alternateEmailAddress = alternateEmailAddress;
        return this;
    }

    public void setAlternateEmailAddress(String alternateEmailAddress) {
        this.alternateEmailAddress = alternateEmailAddress;
    }

    public RelationWithStudentEnum getRelationWithStudent() {
        return relationWithStudent;
    }

    public Student relationWithStudent(RelationWithStudentEnum relationWithStudent) {
        this.relationWithStudent = relationWithStudent;
        return this;
    }

    public void setRelationWithStudent(RelationWithStudentEnum relationWithStudent) {
        this.relationWithStudent = relationWithStudent;
    }

    public String getName() {
        return name;
    }

    public Student name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Student middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Student lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getContactNo() {
        return contactNo;
    }

    public Student contactNo(Long contactNo) {
        this.contactNo = contactNo;
        return this;
    }

    public void setContactNo(Long contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Student emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Status getTransport() {
        return transport;
    }

    public Student transport(Status transport) {
        this.transport = transport;
        return this;
    }

    public void setTransport(Status transport) {
        this.transport = transport;
    }

    public Status getMess() {
        return mess;
    }

    public Student mess(Status mess) {
        this.mess = mess;
        return this;
    }

    public void setMess(Status mess) {
        this.mess = mess;
    }

    public Status getGym() {
        return gym;
    }

    public Student gym(Status gym) {
        this.gym = gym;
        return this;
    }

    public void setGym(Status gym) {
        this.gym = gym;
    }

    public Status getCulturalClass() {
        return culturalClass;
    }

    public Student culturalClass(Status culturalClass) {
        this.culturalClass = culturalClass;
        return this;
    }

    public void setCulturalClass(Status culturalClass) {
        this.culturalClass = culturalClass;
    }

    public Status getLibrary() {
        return library;
    }

    public Student library(Status library) {
        this.library = library;
        return this;
    }

    public void setLibrary(Status library) {
        this.library = library;
    }

    public Status getSports() {
        return sports;
    }

    public Student sports(Status sports) {
        this.sports = sports;
        return this;
    }

    public void setSports(Status sports) {
        this.sports = sports;
    }

    public Status getSwimming() {
        return swimming;
    }

    public Student swimming(Status swimming) {
        this.swimming = swimming;
        return this;
    }

    public void setSwimming(Status swimming) {
        this.swimming = swimming;
    }

    public Status getExtraClass() {
        return extraClass;
    }

    public Student extraClass(Status extraClass) {
        this.extraClass = extraClass;
        return this;
    }

    public void setExtraClass(Status extraClass) {
        this.extraClass = extraClass;
    }

    public Status getHandicrafts() {
        return handicrafts;
    }

    public Student handicrafts(Status handicrafts) {
        this.handicrafts = handicrafts;
        return this;
    }

    public void setHandicrafts(Status handicrafts) {
        this.handicrafts = handicrafts;
    }

    public Status getAdd() {
        return add;
    }

    public Student add(Status add) {
        this.add = add;
        return this;
    }

    public void setAdd(Status add) {
        this.add = add;
    }

    public Long getUploadPhoto() {
        return uploadPhoto;
    }

    public Student uploadPhoto(Long uploadPhoto) {
        this.uploadPhoto = uploadPhoto;
        return this;
    }

    public void setUploadPhoto(Long uploadPhoto) {
        this.uploadPhoto = uploadPhoto;
    }

    public Long getAdmissionNo() {
        return admissionNo;
    }

    public Student admissionNo(Long admissionNo) {
        this.admissionNo = admissionNo;
        return this;
    }

    public void setAdmissionNo(Long admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getRollNo() {
        return rollNo;
    }

    public Student rollNo(String rollNo) {
        this.rollNo = rollNo;
        return this;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public StudentTypeEnum getStudentType() {
        return studentType;
    }

    public Student studentType(StudentTypeEnum studentType) {
        this.studentType = studentType;
        return this;
    }

    public void setStudentType(StudentTypeEnum studentType) {
        this.studentType = studentType;
    }

    public Department getDepartment() {
        return department;
    }

    public Student department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Batch getBatch() {
        return batch;
    }

    public Student batch(Batch batch) {
        this.batch = batch;
        return this;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Section getSection() {
        return section;
    }

    public Student section(Section section) {
        this.section = section;
        return this;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Branch getBranch() {
        return branch;
    }

    public Student branch(Branch branch) {
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
        Student student = (Student) o;
        if (student.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", studentName='" + getStudentName() + "'" +
            ", studentMiddleName='" + getStudentMiddleName() + "'" +
            ", studentLastName='" + getStudentLastName() + "'" +
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
            ", studentContactNumber=" + getStudentContactNumber() +
            ", alternateContactNumber=" + getAlternateContactNumber() +
            ", studentEmailAddress='" + getStudentEmailAddress() + "'" +
            ", alternateEmailAddress='" + getAlternateEmailAddress() + "'" +
            ", relationWithStudent='" + getRelationWithStudent() + "'" +
            ", name='" + getName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", contactNo=" + getContactNo() +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", transport='" + getTransport() + "'" +
            ", mess='" + getMess() + "'" +
            ", gym='" + getGym() + "'" +
            ", culturalClass='" + getCulturalClass() + "'" +
            ", library='" + getLibrary() + "'" +
            ", sports='" + getSports() + "'" +
            ", swimming='" + getSwimming() + "'" +
            ", extraClass='" + getExtraClass() + "'" +
            ", handicrafts='" + getHandicrafts() + "'" +
            ", add='" + getAdd() + "'" +
            ", uploadPhoto=" + getUploadPhoto() +
            ", admissionNo=" + getAdmissionNo() +
            ", rollNo='" + getRollNo() + "'" +
            ", studentType='" + getStudentType() + "'" +
            "}";
    }
}
