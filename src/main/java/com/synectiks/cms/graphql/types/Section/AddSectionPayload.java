package com.synectiks.cms.graphql.types.Section;

import com.synectiks.cms.domain.Section;

public class AddSectionPayload extends AbstractSectionPayload{
    public AddSectionPayload(Section section) {
        super(section);
    }
}
