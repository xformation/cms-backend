package com.synectiks.cms.graphql.types.AdmissionPersonalDetails;

import com.synectiks.commons.entities.cms.enumeration.Gender;

import java.util.Date;
import java.util.Objects;

public class AbstractAdmissionPersonalDetailsInput {
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
    private Date dateOfBirth;
    private String email;
    private Gender sex;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAdmissionPersonalDetailsInput)) return false;
        AbstractAdmissionPersonalDetailsInput that = (AbstractAdmissionPersonalDetailsInput) o;
        return Objects.equals(getId(), that.getId()) &&
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
            getSex() == that.getSex();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStudentName(), getStudentMiddleName(), getStudentLastName(), getFatherName(), getFatherMiddleName(), getFatherLastName(), getMotherName(), getMotherMiddleName(), getMotherLastName(), getContactNumber(), getAlternateMobileNumber(), getDateOfBirth(), getEmail(), getSex());
    }

    @Override
    public String toString() {
        return "AbstractAdmissionPersonalDetailsInput{" +
            "id=" + id +
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
            '}';
    }
}
