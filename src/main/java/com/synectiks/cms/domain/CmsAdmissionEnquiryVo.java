package com.synectiks.cms.domain;

import com.synectiks.cms.domain.enumeration.CourseEnum;
import com.synectiks.cms.domain.enumeration.EnquiryStatus;
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
    private String mobileNumber;
    private String alternateMobileNumber;
    private String email;
    private CourseEnum courseApplyingFor;
    private ModeOfEnquiry modeOfEnquiry;
    private EnquiryStatus status;
    private String description;
    private LocalDate enquiryDate;
    private LocalDate updatedOn;
    private String updatedBy;
    private Branch branch;
    private AdmissionApplication admissionApplication;

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
        if (!(o instanceof CmsAdmissionEnquiryVo)) return false;
        CmsAdmissionEnquiryVo that = (CmsAdmissionEnquiryVo) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getStudentName(), that.getStudentName()) &&
            Objects.equals(getMobileNumber(), that.getMobileNumber()) &&
            Objects.equals(getAlternateMobileNumber(), that.getAlternateMobileNumber()) &&
            Objects.equals(getEmail(), that.getEmail()) &&
            getCourseApplyingFor() == that.getCourseApplyingFor() &&
            getModeOfEnquiry() == that.getModeOfEnquiry() &&
            getStatus() == that.getStatus() &&
            Objects.equals(getDescription(), that.getDescription()) &&
            Objects.equals(getEnquiryDate(), that.getEnquiryDate()) &&
            Objects.equals(getUpdatedOn(), that.getUpdatedOn()) &&
            Objects.equals(getUpdatedBy(), that.getUpdatedBy()) &&
            Objects.equals(getBranch(), that.getBranch()) &&
            Objects.equals(getAdmissionApplication(), that.getAdmissionApplication()) &&
            Objects.equals(getStrEnquiryDate(), that.getStrEnquiryDate()) &&
            Objects.equals(getStrUpdatedOn(), that.getStrUpdatedOn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStudentName(), getMobileNumber(), getAlternateMobileNumber(), getEmail(), getCourseApplyingFor(), getModeOfEnquiry(), getStatus(), getDescription(), getEnquiryDate(), getUpdatedOn(), getUpdatedBy(), getBranch(), getAdmissionApplication(), getStrEnquiryDate(), getStrUpdatedOn());
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

