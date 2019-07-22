package com.synectiks.cms.graphql.types.AdmissionPersonalDetails;

public class AddAdmissionPersonalDetailsInput extends AbstractAdmissionPersonalDetailsInput {
    private Long countryId;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
