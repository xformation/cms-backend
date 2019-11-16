package com.synectiks.cms.graphql.types.Contract;

import org.springframework.lang.Nullable;

public enum TypeOfOwnership   {
    COMPANYOWNED(1, "COMPANYOWNED"),
    CONTRACTUAL(2, "CONTRACTUAL");


    private final int value;
    private final String description;

    TypeOfOwnership(int value, String description){
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

    public static TypeOfOwnership valueOf(int typeOfOwnershipCode) {
        TypeOfOwnership status = resolve(typeOfOwnershipCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + typeOfOwnershipCode + "]");
        }
        return status;
    }


    @Nullable
    public static TypeOfOwnership resolve(int typeOfOwnershipCode) {
        for (TypeOfOwnership status : values()) {
            if (status.value == typeOfOwnershipCode) {
                return status;
            }
        }
        return null;
    }

    @Nullable
    public static TypeOfOwnership getTypeOfOwnershipOnDescription(String typeOfOwnershipDescription) {
        for (TypeOfOwnership status : values()) {
            if (status.description.equalsIgnoreCase(typeOfOwnershipDescription)) {
                return status;
            }
        }
        return null;
    }
}
