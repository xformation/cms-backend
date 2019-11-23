package com.synectiks.cms.graphql.types.TypeOfGrading;

import com.synectiks.commons.entities.cms.TypeOfGrading;

import java.util.List;

public class RemoveTypeOfGradingPayload {
    private final List<TypeOfGrading> typeOfGradings;

    public RemoveTypeOfGradingPayload(List<TypeOfGrading> typeOfGradings){
        this.typeOfGradings = typeOfGradings;
    }
    public List<TypeOfGrading> getTypeOfGradings() {
        return typeOfGradings;
    }
}
