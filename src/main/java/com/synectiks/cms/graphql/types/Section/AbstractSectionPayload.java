package com.synectiks.cms.graphql.types.Section;

import com.synectiks.cms.entities.Section;

public class AbstractSectionPayload {
    private final Section section;

    public AbstractSectionPayload(Section section) {
        this.section = section;
    }

    public Section getSection() {
        return section;
    }
}
