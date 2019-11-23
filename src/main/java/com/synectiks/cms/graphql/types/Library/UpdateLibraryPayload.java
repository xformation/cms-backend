package com.synectiks.cms.graphql.types.Library;

import com.synectiks.commons.entities.cms.Library;

public class UpdateLibraryPayload extends  AbstractLibraryPayload{

    public UpdateLibraryPayload(Library library) {
        super(library);
    }
}
