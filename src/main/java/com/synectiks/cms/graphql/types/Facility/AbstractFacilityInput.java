package com.synectiks.cms.graphql.types.Facility;

import java.util.Objects;

public class AbstractFacilityInput {
    private Long id;
    private String facilityName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractFacilityInput)) return false;
        AbstractFacilityInput that = (AbstractFacilityInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getFacilityName(), that.getFacilityName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFacilityName());
    }

    @Override
    public String toString() {
        return "AbstractFacilityInput{" +
            "id=" + id +
            ", facilityName='" + facilityName + '\'' +
            '}';
    }
}
