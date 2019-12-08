package com.synectiks.cms.graphql.types.Section;

import java.util.List;

import com.synectiks.cms.entities.Section;

public class RemoveSectionPayload {
    private final List<Section>  sections;

    public RemoveSectionPayload(List<Section> sections) {
        this.sections = sections;
    }

    public List<Section> getSections() {
        return sections;
    }
}
