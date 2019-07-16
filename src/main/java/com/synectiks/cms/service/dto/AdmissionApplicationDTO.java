package com.synectiks.cms.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.AdmissionStatusEnum;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.CourseEnum;

/**
 * A DTO for the AdmissionApplication entity.
 */
public class AdmissionApplicationDTO implements Serializable {

    private Long id;

    @NotNull
    private AdmissionStatusEnum admissionStatus;

    @NotNull
    private String studentName;

    private String studentMiddleName;

    private String studentLastName;

    private String fatherName;

    private String fatherMiddleName;

    private String fatherLastName;

    private String motherName;

    private String motherMiddleName;

    private String motherLastName;

    @NotNull
    private String contactNumber;

    private String alternateMobileNumber;

    @NotNull
    private LocalDate dateOfBirth;

    private String email;

    @NotNull
    private Gender sex;

    @NotNull
    private String comments;

    @NotNull
    private Integer applicationId;

    @NotNull
    private String uploadPhoto;

    @NotNull
    private CourseEnum course;

    @NotNull
    private LocalDate admissionDate;


    private Long admissionEnquiryId;

    private Long academicHistoryId;

    private Long documentsId;

    private Long branchId;

    private Long batchId;

    private Long stateId;

    private Long cityId;

    private Long countryId;

    private Long departmentId;

    private Long academicyearId;

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

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Long getAdmissionEnquiryId() {
        return admissionEnquiryId;
    }

    public void setAdmissionEnquiryId(Long admissionEnquiryId) {
        this.admissionEnquiryId = admissionEnquiryId;
    }

    public Long getAcademicHistoryId() {
        return academicHistoryId;
    }

    public void setAcademicHistoryId(Long academicHistoryId) {
        this.academicHistoryId = academicHistoryId;
    }

    public Long getDocumentsId() {
        return documentsId;
    }

    public void setDocumentsId(Long documentsId) {
        this.documentsId = documentsId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicYearId) {
        this.academicyearId = academicYearId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdmissionApplicationDTO admissionApplicationDTO = (AdmissionApplicationDTO) o;
        if (admissionApplicationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), admissionApplicationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdmissionApplicationDTO{" +
            "id=" + getId() +
            ", admissionStatus='" + getAdmissionStatus() + "'" +
            ", studentName='" + getStudentName() + "'" +
            ", studentMiddleName='" + getStudentMiddleName() + "'" +
            ", studentLastName='" + getStudentLastName() + "'" +
            ", fatherName='" + getFatherName() + "'" +
            ", fatherMiddleName='" + getFatherMiddleName() + "'" +
            ", fatherLastName='" + getFatherLastName() + "'" +
            ", motherName='" + getMotherName() + "'" +
            ", motherMiddleName='" + getMotherMiddleName() + "'" +
            ", motherLastName='" + getMotherLastName() + "'" +
            ", contactNumber='" + getContactNumber() + "'" +
            ", alternateMobileNumber='" + getAlternateMobileNumber() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", email='" + getEmail() + "'" +
            ", sex='" + getSex() + "'" +
            ", comments='" + getComments() + "'" +
            ", applicationId=" + getApplicationId() +
            ", uploadPhoto='" + getUploadPhoto() + "'" +
            ", course='" + getCourse() + "'" +
            ", admissionDate='" + getAdmissionDate() + "'" +
            ", admissionEnquiry=" + getAdmissionEnquiryId() +
            ", academicHistory=" + getAcademicHistoryId() +
            ", documents=" + getDocumentsId() +
            ", branch=" + getBranchId() +
            ", batch=" + getBatchId() +
            ", state=" + getStateId() +
            ", city=" + getCityId() +
            ", country=" + getCountryId() +
            ", department=" + getDepartmentId() +
            ", academicyear=" + getAcademicyearId() +
            "}";
    }
}
