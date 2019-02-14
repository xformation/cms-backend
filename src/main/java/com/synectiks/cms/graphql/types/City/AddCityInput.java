package com.synectiks.cms.graphql.types.City;

public class AddCityInput extends AbstractCityInput{
    private Long stateId;

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Override

    public String toString(){
        return "AddCityInput {}" + super.toString();
    }
}
