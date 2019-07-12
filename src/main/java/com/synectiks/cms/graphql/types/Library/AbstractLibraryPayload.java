package com.synectiks.cms.graphql.types.Library;

import com.synectiks.cms.domain.Library;

public class AbstractLibraryPayload {
private final Library library;

    public AbstractLibraryPayload(Library library) {
        this.library = library;
    }

    public Library getLibrary() {
        return library;
    }
}
