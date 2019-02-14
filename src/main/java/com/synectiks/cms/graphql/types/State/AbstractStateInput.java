package com.synectiks.cms.graphql.types.State;

import java.util.Objects;

public class AbstractStateInput {
    private Long id;
    private String stateName;
    private String divisionType;
    private String stateCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getDivisionType() {
        return divisionType;
    }

    public void setDivisionType(String divisionType) {
        this.divisionType = divisionType;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractStateInput that = (AbstractStateInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(stateName, that.stateName) &&
            Objects.equals(divisionType, that.divisionType) &&
            Objects.equals(stateCode, that.stateCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stateName, divisionType, stateCode);
    }

    @Override
    public String toString() {
        return "AbstractStateInput{" +
            "id=" + id +
            ", stateName='" + stateName + '\'' +
            ", divisionType='" + divisionType + '\'' +
            ", stateCode='" + stateCode + '\'' +
            '}';
    }
}

