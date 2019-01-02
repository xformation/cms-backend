package com.synectiks.cms.graphql.types.Section;

import com.synectiks.cms.domain.Section;

public class UpdateSectionPayload extends AbstractSectionPayload{
    public UpdateSectionPayload(Section section) {
        super(section);
    }
}
