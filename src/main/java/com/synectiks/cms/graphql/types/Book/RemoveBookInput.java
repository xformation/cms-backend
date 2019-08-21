package com.synectiks.cms.graphql.types.Book;

public class RemoveBookInput {
    private Long bookId;

    public RemoveBookInput(Long bookId) {
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
