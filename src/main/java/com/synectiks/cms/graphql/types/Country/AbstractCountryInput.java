package com.synectiks.cms.graphql.types.Country;

import java.util.Objects;

public class AbstractCountryInput {
    private Long id;
    private String countryName;
    private String countryCode;
    private String isdCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getIsdCode() {
        return isdCode;
    }

    public void setIsdCode(String isdCode) {
        this.isdCode = isdCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCountryInput that = (AbstractCountryInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(countryName, that.countryName) &&
            Objects.equals(countryCode, that.countryCode) &&
            Objects.equals(isdCode, that.isdCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countryName, countryCode, isdCode);
    }

    @Override
    public String toString() {
        return "AbstractCountryInput{" +
            "id=" + id +
            ", countryName='" + countryName + '\'' +
            ", countryCode='" + countryCode + '\'' +
            ", isdCode='" + isdCode + '\'' +
            '}';
    }
}
