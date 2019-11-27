package com.synectiks.cms.graphql.types.Library;


import com.synectiks.cms.domain.Library;

import java.util.List;

public class RemoveLibraryPayload {
    private final List<Library>libraries;


    public RemoveLibraryPayload(List<Library> libraries) {
        this.libraries = libraries;
    }
}
