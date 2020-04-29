package com.synectiks.cms.filter.library;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LibraryFilterInput {

    private String libraryId;
    private String departmentId;
    private String bookTitle;

    @JsonProperty ("libraryId")
    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }
    @JsonProperty ("departmentId")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    @JsonProperty ("bookTitle")
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
