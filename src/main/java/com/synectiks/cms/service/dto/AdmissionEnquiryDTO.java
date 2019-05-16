package com.synectiks.cms.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.CourseEnum;
import com.synectiks.cms.domain.enumeration.ModeOfEnquiry;
import com.synectiks.cms.domain.enumeration.EnquiryStatus;

/**
 * A DTO for the AdmissionEnquiry entity.
 */
public class AdmissionEnquiryDTO implements Serializable {

    private Long id;

    @NotNull
    private String studentName;

    @NotNull
    private String mobileNumber;

    private String alternateMobileNumber;

    private String email;

    @NotNull
    private CourseEnum courseApplyingFor;

    @NotNull
    private ModeOfEnquiry modeOfEnquiry;

    @NotNull
    private EnquiryStatus status;

    @NotNull
    private String description;

    @NotNull
    private LocalDate enquiryDate;

    private LocalDate updatedOn;

    private String updatedBy;

    private Long branchId;

    private Long admissionApplicationId;

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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getAdmissionApplicationId() {
        return admissionApplicationId;
    }

    public void setAdmissionApplicationId(Long admissionApplicationId) {
        this.admissionApplicationId = admissionApplicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdmissionEnquiryDTO admissionEnquiryDTO = (AdmissionEnquiryDTO) o;
        if (admissionEnquiryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), admissionEnquiryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdmissionEnquiryDTO{" +
            "id=" + getId() +
            ", studentName='" + getStudentName() + "'" +
            ", mobileNumber='" + getMobileNumber() + "'" +
            ", alternateMobileNumber='" + getAlternateMobileNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", courseApplyingFor='" + getCourseApplyingFor() + "'" +
            ", modeOfEnquiry='" + getModeOfEnquiry() + "'" +
            ", status='" + getStatus() + "'" +
            ", description='" + getDescription() + "'" +
            ", enquiryDate='" + getEnquiryDate() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", branch=" + getBranchId() +
            ", admissionApplication=" + getAdmissionApplicationId() +
            "}";
    }
}
