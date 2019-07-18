package com.synectiks.cms.domain;

import com.synectiks.cms.domain.enumeration.CourseEnum;
import com.synectiks.cms.domain.enumeration.Gender;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CmsAdmissionApplicationVo implements Serializable {

    private Long totalReceived = 0L;
    private Long totalInprocess = 0L;
    private Long totalDeclined = 0L;
    private Long totalAccepted = 0L;
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
    private Integer applicationId;
    private String uploadPhoto;
    private CourseEnum course;
    private LocalDate admissionDate;
    private AdmissionEnquiry admissionEnquiry;
    private AcademicHistory academicHistory;
    private Documents documents;
    private Branch branch;
    private Batch batch;
    private State state;
    private City city;
    private Country country;
    private Department department;
    private AcademicYear academicyear;
    private Long academicyearId;
    private Long admissionEnquiryId;
    private Long academicHistoryId;
    private Long documentsId;
    private Long branchId;
    private Long batchId;
    private Long stateId;
    private Long cityId;
    private Long countryId;
    private Long departmentId;
    private String strDateOfBirth;
    private String strAdmissionDate;

    public Long getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(Long totalReceived) {
        this.totalReceived = totalReceived;
    }

    public Long getTotalInprocess() {
        return totalInprocess;
    }

    public void setTotalInprocess(Long totalInprocess) {
        this.totalInprocess = totalInprocess;
    }

    public Long getTotalDeclined() {
        return totalDeclined;
    }

    public void setTotalDeclined(Long totalDeclined) {
        this.totalDeclined = totalDeclined;
    }

    public Long getTotalAccepted() {
        return totalAccepted;
    }

    public void setTotalAccepted(Long totalAccepted) {
        this.totalAccepted = totalAccepted;
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

    public AdmissionEnquiry getAdmissionEnquiry() {
        return admissionEnquiry;
    }

    public void setAdmissionEnquiry(AdmissionEnquiry admissionEnquiry) {
        this.admissionEnquiry = admissionEnquiry;
    }

    public AcademicHistory getAcademicHistory() {
        return academicHistory;
    }

    public void setAcademicHistory(AcademicHistory academicHistory) {
        this.academicHistory = academicHistory;
    }

    public Documents getDocuments() {
        return documents;
    }

    public void setDocuments(Documents documents) {
        this.documents = documents;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public AcademicYear getAcademicyear() {
        return academicyear;
    }

    public void setAcademicyear(AcademicYear academicyear) {
        this.academicyear = academicyear;
    }

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicyearId) {
        this.academicyearId = academicyearId;
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

    public String getStrDateOfBirth() {
        return strDateOfBirth;
    }

    public void setStrDateOfBirth(String strDateOfBirth) {
        this.strDateOfBirth = strDateOfBirth;
    }

    public String getStrAdmissionDate() {
        return strAdmissionDate;
    }

    public void setStrAdmissionDate(String strAdmissionDate) {
        this.strAdmissionDate = strAdmissionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CmsAdmissionApplicationVo)) return false;
        CmsAdmissionApplicationVo that = (CmsAdmissionApplicationVo) o;
        return Objects.equals(getTotalReceived(), that.getTotalReceived()) &&
            Objects.equals(getTotalInprocess(), that.getTotalInprocess()) &&
            Objects.equals(getTotalDeclined(), that.getTotalDeclined()) &&
            Objects.equals(getTotalAccepted(), that.getTotalAccepted()) &&
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
            Objects.equals(getApplicationId(), that.getApplicationId()) &&
            Objects.equals(getUploadPhoto(), that.getUploadPhoto()) &&
            getCourse() == that.getCourse() &&
            Objects.equals(getAdmissionDate(), that.getAdmissionDate()) &&
            Objects.equals(getAdmissionEnquiry(), that.getAdmissionEnquiry()) &&
            Objects.equals(getAcademicHistory(), that.getAcademicHistory()) &&
            Objects.equals(getDocuments(), that.getDocuments()) &&
            Objects.equals(getBranch(), that.getBranch()) &&
            Objects.equals(getBatch(), that.getBatch()) &&
            Objects.equals(getState(), that.getState()) &&
            Objects.equals(getCity(), that.getCity()) &&
            Objects.equals(getCountry(), that.getCountry()) &&
            Objects.equals(getDepartment(), that.getDepartment()) &&
            Objects.equals(getAcademicyear(), that.getAcademicyear()) &&
            Objects.equals(getStrDateOfBirth(), that.getStrDateOfBirth()) &&
            Objects.equals(getStrAdmissionDate(), that.getStrAdmissionDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTotalReceived(), getTotalInprocess(), getTotalDeclined(), getTotalAccepted(), getId(), getStudentName(), getStudentMiddleName(), getStudentLastName(), getFatherName(), getFatherMiddleName(), getFatherLastName(), getMotherName(), getMotherMiddleName(), getMotherLastName(), getContactNumber(), getAlternateMobileNumber(), getDateOfBirth(), getEmail(), getSex(), getComments(), getApplicationId(), getUploadPhoto(), getCourse(), getAdmissionDate(), getAdmissionEnquiry(), getAcademicHistory(), getDocuments(), getBranch(), getBatch(), getState(), getCity(), getCountry(), getDepartment(), getAcademicyear(), getStrDateOfBirth(), getStrAdmissionDate());
    }

    @Override
    public String toString() {
        return "CmsAdmissionApplicationVo{" +
            "totalReceived=" + totalReceived +
            ", totalInprocess=" + totalInprocess +
            ", totalDeclined=" + totalDeclined +
            ", totalAccepted=" + totalAccepted +
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
            ", applicationId=" + applicationId +
            ", uploadPhoto='" + uploadPhoto + '\'' +
            ", course=" + course +
            ", admissionDate=" + admissionDate +
            ", admissionEnquiry=" + admissionEnquiry +
            ", academicHistory=" + academicHistory +
            ", documents=" + documents +
            ", branch=" + branch +
            ", batch=" + batch +
            ", state=" + state +
            ", city=" + city +
            ", country=" + country +
            ", department=" + department +
            ", academicyear=" + academicyear +
            ", strDateOfBirth='" + strDateOfBirth + '\'' +
            ", strAdmissionDate='" + strAdmissionDate + '\'' +
            '}';
    }
}
