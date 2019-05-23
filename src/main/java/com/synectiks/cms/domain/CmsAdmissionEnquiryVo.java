package com.synectiks.cms.domain;

import com.synectiks.cms.domain.enumeration.CourseEnum;
import com.synectiks.cms.domain.enumeration.EnquiryStatus;
import com.synectiks.cms.domain.enumeration.ModeOfEnquiry;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class CmsAdmissionEnquiryVo implements Serializable {

    private Long id;
    private String studentName;
    private String mobileNumber;
    private String alternateMobileNumber;
    private String email;
    private CourseEnum courseApplyingFor;
    private ModeOfEnquiry modeOfEnquiry;
    private EnquiryStatus status;
    private String description;
    private Date enquiryDate;
    private Date updatedOn;
    private String updatedBy;
    private Branch branch;
    private AdmissionApplication admissionApplication;

    private String strEnquiryDate;
    private String strUpdatedOn;

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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public AdmissionApplication getAdmissionApplication() {
        return admissionApplication;
    }

    public void setAdmissionApplication(AdmissionApplication admissionApplication) {
        this.admissionApplication = admissionApplication;
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
        if (o == null || getClass() != o.getClass()) return false;
        CmsAdmissionEnquiryVo that = (CmsAdmissionEnquiryVo) o;
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
            Objects.equals(updatedBy, that.updatedBy) &&
            Objects.equals(branch, that.branch) &&
            Objects.equals(admissionApplication, that.admissionApplication) &&
            Objects.equals(strEnquiryDate, that.strEnquiryDate) &&
            Objects.equals(strUpdatedOn, that.strUpdatedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentName, mobileNumber, alternateMobileNumber, email, courseApplyingFor, modeOfEnquiry, status, description, enquiryDate, updatedOn, updatedBy, branch, admissionApplication, strEnquiryDate, strUpdatedOn);
    }

    @Override
    public String toString() {
        return "CmsAdmissionEnquiryVo{" +
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
            ", branch=" + branch +
            ", admissionApplication=" + admissionApplication +
            ", strEnquiryDate='" + strEnquiryDate + '\'' +
            ", strUpdatedOn='" + strUpdatedOn + '\'' +
            '}';
    }
}
