package com.synectiks.cms.graphql.types.Periods;

public class AddPeriodsInput extends AbstractPeriodsInput {
    public Long sectionId;

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public String toString() {
        return "AddPeriodsInput{" +
            ", sectionId=" + sectionId +
            '}'+ super.toString();
    }
}
