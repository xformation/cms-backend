package com.synectiks.cms.entities;

import java.util.Date;

import com.synectiks.cms.entities.enumeration.Status;

public class CmsLibrary {
    private Library library;
    private String bookTitle;
    private Date dateOfIssue;
    private DueDate dueDate;
    private Student student;
    private Department department;
    private String noOfCopies;
    private String copiesAvailable;

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public DueDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(DueDate dueDate) {
        this.dueDate = dueDate;
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

    public String getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(String noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    public String getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(String copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }
}
