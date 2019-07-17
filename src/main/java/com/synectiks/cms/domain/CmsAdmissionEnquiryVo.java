package com.synectiks.cms.domain;

import com.synectiks.cms.domain.enumeration.CourseEnum;
import com.synectiks.cms.domain.enumeration.EnquiryStatus;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.ModeOfEnquiry;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CmsAdmissionEnquiryVo implements Serializable {

    private Long totalAdmissions = 0L;
    private Long totalFollowup = 0L;
    private Long totalDeclined = 0L;
    private Long totalConverted = 0L;
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
    private String contactNumber;
    private String alternateMobileNumber;
    private LocalDate dateOfBirth;
    private String email;
    private Gender sex;
    private String comments;
    private CourseEnum courseApplyingFor;
    private String highestQualification;
    private ModeOfEnquiry modeOfEnquiry;
    private EnquiryStatus status;
    private String description;
    private LocalDate enquiryDate;
    private LocalDate updatedOn;
    private String updatedBy;
    private Branch branch;
    private Department department;
    private Batch batch;
    private State state;
    private City city;
    private Country country;

    private String strDateOfBirth;
    private String strEnquiryDate;
    private String strUpdatedOn;

    public Long getTotalAdmissions() {
        return totalAdmissions;
    }

    public void setTotalAdmissions(Long totalAdmissions) {
        this.totalAdmissions = totalAdmissions;
    }

    public Long getTotalFollowup() {
        return totalFollowup;
    }

    public void setTotalFollowup(Long totalFollowup) {
        this.totalFollowup = totalFollowup;
    }

    public Long getTotalDeclined() {
        return totalDeclined;
    }

    public void setTotalDeclined(Long totalDeclined) {
        this.totalDeclined = totalDeclined;
    }

    public Long getTotalConverted() {
        return totalConverted;
    }

    public void setTotalConverted(Long totalConverted) {
        this.totalConverted = totalConverted;
    }

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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAlternateMobileNumber() {
        return alternateMobileNumber;
    }

    public void setAlternateMobileNumber(String alternateMobileNumber) {
        this.alternateMobileNumber = alternateMobileNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public CourseEnum getCourseApplyingFor() {
        return courseApplyingFor;
    }

    public void setCourseApplyingFor(CourseEnum courseApplyingFor) {
        this.courseApplyingFor = courseApplyingFor;
    }

    public String getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(String highestQualification) {
        this.highestQualification = highestQualification;
    }

    public ModeOfEnquiry getModeOfEnquiry() {
        return modeOfEnquiry;
    }

    public void setModeOfEnquiry(ModeOfEnquiry modeOfEnquiry) {
        this.modeOfEnquiry = modeOfEnquiry;
    }

    public EnquiryStatus getStatus() {
        return status;
    }

    public void setStatus(EnquiryStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(LocalDate enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getStrDateOfBirth() {
        return strDateOfBirth;
    }

    public void setStrDateOfBirth(String strDateOfBirth) {
        this.strDateOfBirth = strDateOfBirth;
    }

    public String getStrEnquiryDate() {
        return strEnquiryDate;
    }

    public void setStrEnquiryDate(String strEnquiryDate) {
        this.strEnquiryDate = strEnquiryDate;
    }

    public String getStrUpdatedOn() {
        return strUpdatedOn;
    }

    public void setStrUpdatedOn(String strUpdatedOn) {
        this.strUpdatedOn = strUpdatedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CmsAdmissionEnquiryVo)) return false;
        CmsAdmissionEnquiryVo that = (CmsAdmissionEnquiryVo) o;
        return Objects.equals(getTotalAdmissions(), that.getTotalAdmissions()) &&
            Objects.equals(getTotalFollowup(), that.getTotalFollowup()) &&
            Objects.equals(getTotalDeclined(), that.getTotalDeclined()) &&
            Objects.equals(getTotalConverted(), that.getTotalConverted()) &&
            Objects.equals(getId(), that.getId()) &&
            Objects.equals(getStudentName(), that.getStudentName()) &&
            Objects.equals(getStudentMiddleName(), that.getStudentMiddleName()) &&
            Objects.equals(getStudentLastName(), that.getStudentLastName()) &&
            Objects.equals(getFatherName(), that.getFatherName()) &&
            Objects.equals(getFatherMiddleName(), that.getFatherMiddleName()) &&
            Objects.equals(getFatherLastName(), that.getFatherLastName()) &&
            Objects.equals(getMotherName(), that.getMotherName()) &&
            Objects.equals(getMotherMiddleName(), that.getMotherMiddleName()) &&
            Objects.equals(getMotherLastName(), that.getMotherLastName()) &&
            Objects.equals(getContactNumber(), that.getContactNumber()) &&
            Objects.equals(getAlternateMobileNumber(), that.getAlternateMobileNumber()) &&
            Objects.equals(getDateOfBirth(), that.getDateOfBirth()) &&
            Objects.equals(getEmail(), that.getEmail()) &&
            getSex() == that.getSex() &&
            Objects.equals(getComments(), that.getComments()) &&
            getCourseApplyingFor() == that.getCourseApplyingFor() &&
            Objects.equals(getHighestQualification(), that.getHighestQualification()) &&
            getModeOfEnquiry() == that.getModeOfEnquiry() &&
            getStatus() == that.getStatus() &&
            Objects.equals(getDescription(), that.getDescription()) &&
            Objects.equals(getEnquiryDate(), that.getEnquiryDate()) &&
            Objects.equals(getUpdatedOn(), that.getUpdatedOn()) &&
            Objects.equals(getUpdatedBy(), that.getUpdatedBy()) &&
            Objects.equals(getBranch(), that.getBranch()) &&
            Objects.equals(getDepartment(), that.getDepartment()) &&
            Objects.equals(getBatch(), that.getBatch()) &&
            Objects.equals(getState(), that.getState()) &&
            Objects.equals(getCity(), that.getCity()) &&
            Objects.equals(getCountry(), that.getCountry()) &&
            Objects.equals(getStrDateOfBirth(), that.getStrDateOfBirth()) &&
            Objects.equals(getStrEnquiryDate(), that.getStrEnquiryDate()) &&
            Objects.equals(getStrUpdatedOn(), that.getStrUpdatedOn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTotalAdmissions(), getTotalFollowup(), getTotalDeclined(), getTotalConverted(), getId(), getStudentName(), getStudentMiddleName(), getStudentLastName(), getFatherName(), getFatherMiddleName(), getFatherLastName(), getMotherName(), getMotherMiddleName(), getMotherLastName(), getContactNumber(), getAlternateMobileNumber(), getDateOfBirth(), getEmail(), getSex(), getComments(), getCourseApplyingFor(), getHighestQualification(), getModeOfEnquiry(), getStatus(), getDescription(), getEnquiryDate(), getUpdatedOn(), getUpdatedBy(), getBranch(), getDepartment(), getBatch(), getState(), getCity(), getCountry(), getStrDateOfBirth(), getStrEnquiryDate(), getStrUpdatedOn());
    }

    @Override
    public String toString() {
        return "CmsAdmissionEnquiryVo{" +
            "totalAdmissions=" + totalAdmissions +
            ", totalFollowup=" + totalFollowup +
            ", totalDeclined=" + totalDeclined +
            ", totalConverted=" + totalConverted +
            ", id=" + id +
            ", studentName='" + studentName + '\'' +
            ", studentMiddleName='" + studentMiddleName + '\'' +
            ", studentLastName='" + studentLastName + '\'' +
            ", fatherName='" + fatherName + '\'' +
            ", fatherMiddleName='" + fatherMiddleName + '\'' +
            ", fatherLastName='" + fatherLastName + '\'' +
            ", motherName='" + motherName + '\'' +
            ", motherMiddleName='" + motherMiddleName + '\'' +
            ", motherLastName='" + motherLastName + '\'' +
            ", contactNumber='" + contactNumber + '\'' +
            ", alternateMobileNumber='" + alternateMobileNumber + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", email='" + email + '\'' +
            ", sex=" + sex +
            ", comments='" + comments + '\'' +
            ", courseApplyingFor=" + courseApplyingFor +
            ", highestQualification='" + highestQualification + '\'' +
            ", modeOfEnquiry=" + modeOfEnquiry +
            ", status=" + status +
            ", description='" + description + '\'' +
            ", enquiryDate=" + enquiryDate +
            ", updatedOn=" + updatedOn +
            ", updatedBy='" + updatedBy + '\'' +
            ", branch=" + branch +
            ", department=" + department +
            ", batch=" + batch +
            ", state=" + state +
            ", city=" + city +
            ", country=" + country +
            ", strDateOfBirth='" + strDateOfBirth + '\'' +
            ", strEnquiryDate='" + strEnquiryDate + '\'' +
            ", strUpdatedOn='" + strUpdatedOn + '\'' +
            '}';
    }
}

