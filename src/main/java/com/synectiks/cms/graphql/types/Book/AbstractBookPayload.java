package com.synectiks.cms.graphql.types.Book;

import com.synectiks.cms.entities.Book;

public class AbstractBookPayload {
    private final Book book;

    public AbstractBookPayload(Book book) { this.book = book; }
    public Book getBook() { return book; }
}
