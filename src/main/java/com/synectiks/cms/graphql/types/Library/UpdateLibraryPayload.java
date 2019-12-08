package com.synectiks.cms.graphql.types.Library;

import com.synectiks.cms.entities.Library;

public class UpdateLibraryPayload extends  AbstractLibraryPayload{

    public UpdateLibraryPayload(Library library) {
        super(library);
    }
}
