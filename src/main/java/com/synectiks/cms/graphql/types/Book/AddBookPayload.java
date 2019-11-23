package com.synectiks.cms.graphql.types.Book;

import com.synectiks.commons.entities.cms.Book;

public class AddBookPayload extends AbstractBookPayload {
    public AddBookPayload(Book book) {
        super(book);
    }

}
