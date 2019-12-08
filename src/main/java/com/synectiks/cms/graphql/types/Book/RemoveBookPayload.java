package com.synectiks.cms.graphql.types.Book;

import java.util.List;

import com.synectiks.cms.entities.Book;

public class RemoveBookPayload {
    private final List<Book> books;

    public RemoveBookPayload(List<Book> books) {
        this.books = books;
    }
}
