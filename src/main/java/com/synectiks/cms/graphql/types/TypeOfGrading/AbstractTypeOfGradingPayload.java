package com.synectiks.cms.graphql.types.TypeOfGrading;

import com.synectiks.cms.entities.TypeOfGrading;

public class AbstractTypeOfGradingPayload {

    private final TypeOfGrading typeOfGrading;

    public AbstractTypeOfGradingPayload(TypeOfGrading typeOfGrading) {
        this.typeOfGrading = typeOfGrading;
    }

    public TypeOfGrading getTypeOfGrading() {
        return typeOfGrading;
    }
}
