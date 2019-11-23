package com.synectiks.cms.graphql.types.Section;

import com.synectiks.commons.entities.cms.Section;

import java.util.List;

public class RemoveSectionPayload {
    private final List<Section>  sections;

    public RemoveSectionPayload(List<Section> sections) {
        this.sections = sections;
    }

    public List<Section> getSections() {
        return sections;
    }
}
