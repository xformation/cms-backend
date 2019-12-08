package com.synectiks.cms.entities;

import org.jetbrains.annotations.NotNull;

import com.synectiks.cms.entities.enumeration.StatusEnum;

import java.time.LocalDate;

public class CmsBook implements Comparable<CmsBook> {
    private Long id;

    private Long studentId;
    private Student student;
    private Library library;
    private Long libraryId;


    private String strIssueDate;
    private String strDueDate;
    private String strRecDate;

    private LocalDate issueDate;
    private Integer noOfCopiesAvailable;
    private LocalDate dueDate;
    private StatusEnum status;
    private LocalDate receivedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
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

    public String getStrRecDate() {
        return strRecDate;
    }

    public void setStrRecDate(String strRecDate) {
        this.strRecDate = strRecDate;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public Integer getNoOfCopiesAvailable() {
        return noOfCopiesAvailable;
    }

    public void setNoOfCopiesAvailable(Integer noOfCopiesAvailable) {
        this.noOfCopiesAvailable = noOfCopiesAvailable;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Override
    public int compareTo(CmsBook o) {
        return this.id.compareTo(o.getId());
    }
}


