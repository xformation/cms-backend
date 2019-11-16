package com.synectiks.cms.graphql.types.Insurance;

import org.springframework.lang.Nullable;

public enum TypeOfInsurance {
    LIABILITY(1, "LIABILITY"),
    COLLISION(2, "COLLISION"),
    COMPREHENSIVE(3, "COMPREHENSIVE");

    private final int value;
    private final String description;

    TypeOfInsurance(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int value() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    public static TypeOfInsurance valueOf(int typeOfInsuranceCode) {
        TypeOfInsurance status = resolve(typeOfInsuranceCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + typeOfInsuranceCode + "]");
        }
        return status;
    }


    @Nullable
    public static TypeOfInsurance resolve(int typeOfInsuranceCode) {
        for (TypeOfInsurance status : values()) {
            if (status.value == typeOfInsuranceCode) {
                return status;
            }
        }
        return null;
    }

    @Nullable
    public static TypeOfInsurance getTypeOfInsuranceOnDescription(String typeOfInsuranceDescription) {
        for (TypeOfInsurance status : values()) {
            if (status.description.equalsIgnoreCase(typeOfInsuranceDescription)) {
                return status;
            }
        }
        return null;
    }

}
