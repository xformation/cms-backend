package com.synectiks.cms.graphql.types.AdmissionEnquiry;

import java.util.Date;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.CourseEnum;
import com.synectiks.cms.domain.enumeration.ModeOfEnquiry;
import com.synectiks.cms.domain.enumeration.EnquiryStatus;

public class AbstractAdmissionEnquiryInput {

    private Long id;
    private String studentName;
    private String mobileNumber ;
    private String alternateMobileNumber;
    private String email;
    private CourseEnum courseApplyingFor;
    private ModeOfEnquiry modeOfEnquiry;
    private EnquiryStatus status;
    private String description;
    private Date  enquiryDate;
    private Date updatedOn;
    private String updatedBy;

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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAlternateMobileNumber() {
        return alternateMobileNumber;
    }

    public void setAlternateMobileNumber(String alternateMobileNumber) {
        this.alternateMobileNumber = alternateMobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CourseEnum getCourseApplyingFor() {
        return courseApplyingFor;
    }

    public void setCourseApplyingFor(CourseEnum courseApplyingFor) {
        this.courseApplyingFor = courseApplyingFor;
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

    public Date getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(Date enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAdmissionEnquiryInput that = (AbstractAdmissionEnquiryInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(studentName, that.studentName) &&
            Objects.equals(mobileNumber, that.mobileNumber) &&
            Objects.equals(alternateMobileNumber, that.alternateMobileNumber) &&
            Objects.equals(email, that.email) &&
            courseApplyingFor == that.courseApplyingFor &&
            modeOfEnquiry == that.modeOfEnquiry &&
            status == that.status &&
            Objects.equals(description, that.description) &&
            Objects.equals(enquiryDate, that.enquiryDate) &&
            Objects.equals(updatedOn, that.updatedOn) &&
            Objects.equals(updatedBy, that.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentName, mobileNumber, alternateMobileNumber, email, courseApplyingFor, modeOfEnquiry, status, description, enquiryDate, updatedOn, updatedBy);
    }

    @Override
    public String toString() {
        return "AbstractAdmissionEnquiryInput{" +
            "id=" + id +
            ", studentName='" + studentName + '\'' +
            ", mobileNumber='" + mobileNumber + '\'' +
            ", alternateMobileNumber='" + alternateMobileNumber + '\'' +
            ", email='" + email + '\'' +
            ", courseApplyingFor=" + courseApplyingFor +
            ", modeOfEnquiry=" + modeOfEnquiry +
            ", status=" + status +
            ", description='" + description + '\'' +
            ", enquiryDate=" + enquiryDate +
            ", updatedOn=" + updatedOn +
            ", updatedBy='" + updatedBy + '\'' +
            '}';
    }
}
