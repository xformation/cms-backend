package com.synectiks.cms.graphql.types.AdmissionApplication;

import java.util.Date;
import java.util.Objects;

import com.synectiks.commons.entities.cms.enumeration.AdmissionStatusEnum;
import com.synectiks.commons.entities.cms.enumeration.CourseEnum;
import com.synectiks.commons.entities.cms.enumeration.Gender;
import com.synectiks.commons.entities.cms.enumeration.Status;

public class AbstractAdmissionApplicationInput
{
    private Long id;
    private AdmissionStatusEnum admissionStatus;
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
    private Date dateOfBirth;
    private String email;
    private Gender sex;
    private String comments;
    private Integer applicationId;
    private String uploadPhoto;
    private CourseEnum course;
    private Date admissionDate;
    private Status status;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdmissionStatusEnum getAdmissionStatus() {
        return admissionStatus;
    }

    public void setAdmissionStatus(AdmissionStatusEnum admissionStatus) {
        this.admissionStatus = admissionStatus;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getUploadPhoto() {
        return uploadPhoto;
    }

    public void setUploadPhoto(String uploadPhoto) {
        this.uploadPhoto = uploadPhoto;
    }

    public CourseEnum getCourse() {
        return course;
    }

    public void setCourse(CourseEnum course) {
        this.course = course;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAdmissionApplicationInput)) return false;
        AbstractAdmissionApplicationInput that = (AbstractAdmissionApplicationInput) o;
        return Objects.equals(getId(), that.getId()) &&
            getAdmissionStatus() == that.getAdmissionStatus() &&
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
            Objects.equals(getApplicationId(), that.getApplicationId()) &&
            Objects.equals(getUploadPhoto(), that.getUploadPhoto()) &&
            getCourse() == that.getCourse() &&
            Objects.equals(getAdmissionDate(), that.getAdmissionDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAdmissionStatus(), getStudentName(), getStudentMiddleName(), getStudentLastName(), getFatherName(), getFatherMiddleName(), getFatherLastName(), getMotherName(), getMotherMiddleName(), getMotherLastName(), getContactNumber(), getAlternateMobileNumber(), getDateOfBirth(), getEmail(), getSex(), getComments(), getApplicationId(), getUploadPhoto(), getCourse(), getAdmissionDate());
    }

    @Override
    public String toString() {
        return "AbstractAdmissionApplicationInput{" +
            "id=" + id +
            ", admissionStatus=" + admissionStatus +
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
            ", applicationId=" + applicationId +
            ", uploadPhoto='" + uploadPhoto + '\'' +
            ", course=" + course +
            ", admissionDate=" + admissionDate +
            '}';
    }

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
