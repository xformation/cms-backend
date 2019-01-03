package com.synectiks.cms.graphql.types.Location;

import java.util.Objects;

public class AbstractLocationInput {
    private Long id;
    private String name;
    private String address;
    private String appliesTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAppliesTo() {
        return appliesTo;
    }

    public void setAppliesTo(String appliesTo) {
        this.appliesTo = appliesTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractLocationInput)) return false;
        AbstractLocationInput that = (AbstractLocationInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getAddress(), that.getAddress()) &&
            Objects.equals(getAppliesTo(), that.getAppliesTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getAppliesTo());
    }

    @Override
    public String toString() {
        return "AbstractLocationInput{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", appliesTo='" + appliesTo + '\'' +
            '}';
    }
}
