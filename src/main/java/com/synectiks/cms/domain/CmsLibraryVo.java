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

public class CmsLibraryVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String rowName;
    private String bookTitle;
    private Long bookNo;
    private String author;
    private Long noOfCopies;
    private Long uniqueNo;
    private Long departmentId;
    private Department department;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private List<CmsLibraryVo> dataList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Long getBookNo() {
        return bookNo;
    }

    public void setBookNo(Long bookNo) {
        this.bookNo = bookNo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(Long noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    public Long getUniqueNo() {
        return uniqueNo;
    }

    public void setUniqueNo(Long uniqueNo) {
        this.uniqueNo = uniqueNo;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<CmsLibraryVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsLibraryVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsLibraryVo{" +
            "id=" + id +
            ", rowName='" + rowName + '\'' +
            ", bookTitle=" + bookTitle +
            ", bookNo='" + bookNo + '\'' +
            ", author=" + author +
            ", noOfCopies=" + noOfCopies +
            ", uniqueNo='" + uniqueNo + '\'' +
            ", departmentId='" + departmentId + '\'' +
            ", department='" + department + '\'' +
            ", dataList=" + dataList +
            ", getExitCode()=" + getExitCode() + ", " +
            ", getExitDescription()=" + getExitDescription() + ", " +
            ", getClass()=" + getClass() + ", " +
            ", hashCode()=" + hashCode() + ", " +
            ", toString()=" + super.toString() + "]";
    }
}
