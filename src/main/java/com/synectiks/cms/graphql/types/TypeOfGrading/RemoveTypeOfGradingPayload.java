package com.synectiks.cms.graphql.types.TypeOfGrading;

import java.util.List;

import com.synectiks.cms.entities.TypeOfGrading;

public class RemoveTypeOfGradingPayload {
    private final List<TypeOfGrading> typeOfGradings;

    public RemoveTypeOfGradingPayload(List<TypeOfGrading> typeOfGradings){
        this.typeOfGradings = typeOfGradings;
    }
    public List<TypeOfGrading> getTypeOfGradings() {
        return typeOfGradings;
    }
}
