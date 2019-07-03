package com.synectiks.cms.filter.Book;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookListFilterInput {
    private String bookTitle;
    private String author;
    private String departmentId;
    private String batchId;
    private String subjectId;

    @JsonProperty ("bookTitle")
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @JsonProperty ("departmentId")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    @JsonProperty ("batchId")
    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
    @JsonProperty ("subjectId")
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
