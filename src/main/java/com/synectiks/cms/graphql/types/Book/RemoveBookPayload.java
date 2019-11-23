package com.synectiks.cms.graphql.types.Book;

import com.synectiks.commons.entities.cms.Book;

import java.util.List;

public class RemoveBookPayload {
    private final List<Book> books;

    public RemoveBookPayload(List<Book> books) {
        this.books = books;
    }
}
