package com.synectiks.cms.graphql.types.AddNewBook;

import com.synectiks.cms.domain.AddNewBook;

import java.util.List;

public class RemoveAddNewBookPayload {
    private final List<AddNewBook>addNewBooks;

    public RemoveAddNewBookPayload(List<AddNewBook> addNewBooks) {
        this.addNewBooks = addNewBooks;
    }

    public List<AddNewBook> getAddNewBooks() {
        return addNewBooks;
    }
}
