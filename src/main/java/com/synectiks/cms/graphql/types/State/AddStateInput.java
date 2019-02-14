package com.synectiks.cms.graphql.types.State;

public class AddStateInput extends AbstractStateInput {

    private Long countryId;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "AddStateInput{" +
            "countryId=" + countryId +
            '}';
    }
}
