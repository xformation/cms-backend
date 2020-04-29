package com.synectiks.cms.filter.Book;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookListFilterInput {
    private String studentId;
    private String bookId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
