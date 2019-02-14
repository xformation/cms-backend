package com.synectiks.cms.graphql.types.City;

import java.util.Objects;

public class AbstractCityInput {
    private Long id;
    private String cityName;
    private String cityCode;
    private String stdCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getStdCode() {
        return stdCode;
    }

    public void setStdCode(String stdCode) {
        this.stdCode = stdCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCityInput that = (AbstractCityInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(cityName, that.cityName) &&
            Objects.equals(cityCode, that.cityCode) &&
            Objects.equals(stdCode, that.stdCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cityName, cityCode, stdCode);
    }

    @Override
    public String toString() {
        return "AbstractCityInput{" +
            "id=" + id +
            ", cityName='" + cityName + '\'' +
            ", cityCode='" + cityCode + '\'' +
            ", stdCode='" + stdCode + '\'' +
            '}';
    }
}
