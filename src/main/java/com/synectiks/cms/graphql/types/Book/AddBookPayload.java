package com.synectiks.cms.graphql.types.Book;

import com.synectiks.cms.entities.Book;

public class AddBookPayload extends AbstractBookPayload {
    public AddBookPayload(Book book) {
        super(book);
    }

}
