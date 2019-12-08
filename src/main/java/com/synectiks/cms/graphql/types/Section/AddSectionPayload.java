package com.synectiks.cms.graphql.types.Section;

import com.synectiks.cms.entities.Section;

public class AddSectionPayload extends AbstractSectionPayload{
    public AddSectionPayload(Section section) {
        super(section);
    }
}
