package com.synectiks.cms.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.synectiks.cms.domain.enumeration.CourseEnum;

import com.synectiks.cms.domain.enumeration.ModeOfEnquiry;

import com.synectiks.cms.domain.enumeration.EnquiryStatus;

/**
 * A AdmissionEnquiry.
 */
@Entity
@Table(name = "admission_enquiry")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "admissionenquiry")
public class AdmissionEnquiry implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "student_name", nullable = false)
    private String studentName;

    @NotNull
    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(name = "alternate_mobile_number")
    private String alternateMobileNumber;

    @Column(name = "email")
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "course_applying_for", nullable = false)
    private CourseEnum courseApplyingFor;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "mode_of_enquiry", nullable = false)
    private ModeOfEnquiry modeOfEnquiry;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EnquiryStatus status;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "enquiry_date", nullable = false)
    private Date enquiryDate;

    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @ManyToOne
    @JsonIgnoreProperties("admissionEnquiries")
    private Branch branch;

    @ManyToOne
    @JsonIgnoreProperties("admissionEnquiries")
    private AdmissionApplication admissionApplication;

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

    public AdmissionEnquiry studentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public AdmissionEnquiry mobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAlternateMobileNumber() {
        return alternateMobileNumber;
    }

    public AdmissionEnquiry alternateMobileNumber(String alternateMobileNumber) {
        this.alternateMobileNumber = alternateMobileNumber;
        return this;
    }

    public void setAlternateMobileNumber(String alternateMobileNumber) {
        this.alternateMobileNumber = alternateMobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public AdmissionEnquiry email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CourseEnum getCourseApplyingFor() {
        return courseApplyingFor;
    }

    public AdmissionEnquiry courseApplyingFor(CourseEnum courseApplyingFor) {
        this.courseApplyingFor = courseApplyingFor;
        return this;
    }

    public void setCourseApplyingFor(CourseEnum courseApplyingFor) {
        this.courseApplyingFor = courseApplyingFor;
    }

    public ModeOfEnquiry getModeOfEnquiry() {
        return modeOfEnquiry;
    }

    public AdmissionEnquiry modeOfEnquiry(ModeOfEnquiry modeOfEnquiry) {
        this.modeOfEnquiry = modeOfEnquiry;
        return this;
    }

    public void setModeOfEnquiry(ModeOfEnquiry modeOfEnquiry) {
        this.modeOfEnquiry = modeOfEnquiry;
    }

    public EnquiryStatus getStatus() {
        return status;
    }

    public AdmissionEnquiry status(EnquiryStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(EnquiryStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public AdmissionEnquiry description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEnquiryDate() {
        return enquiryDate;
    }

    public AdmissionEnquiry enquiryDate(Date enquiryDate) {
        this.enquiryDate = enquiryDate;
        return this;
    }

    public void setEnquiryDate(Date enquiryDate) {
        this.enquiryDate = enquiryDate;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public AdmissionEnquiry updatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public AdmissionEnquiry updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Branch getBranch() {
        return branch;
    }

    public AdmissionEnquiry branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public AdmissionApplication getAdmissionApplication() {
        return admissionApplication;
    }

    public AdmissionEnquiry admissionApplication(AdmissionApplication admissionApplication) {
        this.admissionApplication = admissionApplication;
        return this;
    }

    public void setAdmissionApplication(AdmissionApplication admissionApplication) {
        this.admissionApplication = admissionApplication;
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
        AdmissionEnquiry admissionEnquiry = (AdmissionEnquiry) o;
        if (admissionEnquiry.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), admissionEnquiry.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdmissionEnquiry{" +
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
            "}";
    }
}
