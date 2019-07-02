package com.synectiks.cms.graphql.types.AddNewBook;

import com.synectiks.cms.domain.AddNewBook;

public class AbstractAddNewBookPayload {
private final AddNewBook addNewBook;

    public AbstractAddNewBookPayload(AddNewBook addNewBook) {
        this.addNewBook = addNewBook;
    }

    public AddNewBook getAddNewBook() {
        return addNewBook;
    }
}
