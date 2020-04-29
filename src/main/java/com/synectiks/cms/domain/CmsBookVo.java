package com.synectiks.cms.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.cms.domain.enumeration.TypeOfOwnerShip;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CmsBookVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private  Integer noOfCopiesAvailable;
    private String bookStatus;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate receivedDate;
    private String strIssueDate;
    private String strDueDate;
    private String strReceivedDate;
    private Long batchId;
    private Long departmentId;
    private Long libraryId;
    private Long studentId;
    private Library library;
    private Student student;
    private Department department;
    private Batch batch;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private List<CmsBookVo> dataList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNoOfCopiesAvailable() {
        return noOfCopiesAvailable;
    }

    public void setNoOfCopiesAvailable(Integer noOfCopiesAvailable) {
        this.noOfCopiesAvailable = noOfCopiesAvailable;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getStrIssueDate() {
        return strIssueDate;
    }

    public void setStrIssueDate(String strIssueDate) {
        this.strIssueDate = strIssueDate;
    }

    public String getStrDueDate() {
        return strDueDate;
    }

    public void setStrDueDate(String strDueDate) {
        this.strDueDate = strDueDate;
    }

    public String getStrReceivedDate() {
        return strReceivedDate;
    }

    public void setStrReceivedDate(String strReceivedDate) {
        this.strReceivedDate = strReceivedDate;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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

    public List<CmsBookVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsBookVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsBookVo{" +
            "id=" + id +
            ", bookStatus='" + bookStatus + '\'' +
            ", noOfCopiesAvailable=" + noOfCopiesAvailable +
            ", receivedDate='" + receivedDate + '\'' +
            ", issueDate=" + issueDate +
            ", dueDate=" + dueDate +
            ", strIssueDate='" + strIssueDate + '\'' +
            ", strDueDate='" + strDueDate + '\'' +
            ", strReceivedDate='" + strReceivedDate + '\'' +
            ", batchId='" + batchId + '\'' +
            ", department='" + department + '\'' +
            ", batch='" + batch + '\'' +
            ", departmentId='" + departmentId + '\'' +
            ", library='" + library + '\'' +
            ", libraryId='" + libraryId + '\'' +
            ", student='" + student + '\'' +
            ", studentId='" + studentId + '\'' +
            ", dataList=" + dataList +
            ", getExitCode()=" + getExitCode() + ", " +
            ", getExitDescription()=" + getExitDescription() + ", " +
            ", getClass()=" + getClass() + ", " +
            ", hashCode()=" + hashCode() + ", " +
            ", toString()=" + super.toString() + "]";
    }
}
