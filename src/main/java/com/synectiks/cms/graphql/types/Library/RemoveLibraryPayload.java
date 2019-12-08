package com.synectiks.cms.graphql.types.Library;


import java.util.List;

import com.synectiks.cms.entities.Library;

public class RemoveLibraryPayload {
    private final List<Library>libraries;


    public RemoveLibraryPayload(List<Library> libraries) {
        this.libraries = libraries;
    }
}
