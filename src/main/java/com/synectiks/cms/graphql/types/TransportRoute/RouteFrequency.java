package com.synectiks.cms.graphql.types.TransportRoute;


import org.springframework.lang.Nullable;

public enum RouteFrequency {
    MORNINGPICKUP(1, "MORNINGPICKUP"),
    AFTERNOONDROPANDPICKUP(2, "AFTERNOONDROPANDPICKUP"),
    AFTERNOONDROP(3, "AFTERNOONDROP"),

    EVENINGDROP(3, "EVENINGDROP");


    private final int value;
    private final String description;

    RouteFrequency(int value, String description){
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

    public static RouteFrequency valueOf(int routeFrequencyCode) {
        RouteFrequency status = resolve(routeFrequencyCode);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + routeFrequencyCode + "]");
        }
        return status;
    }


    @Nullable
    public static RouteFrequency resolve(int routeFrequencyCode) {
        for (RouteFrequency status : values()) {
            if (status.value == routeFrequencyCode) {
                return status;
            }
        }
        return null;
    }

    @Nullable
    public static RouteFrequency getRouteFrequencyDescription(String routeFrequencyDescription) {
        for (RouteFrequency status : values()) {
            if (status.description.equalsIgnoreCase(routeFrequencyDescription)) {
                return status;
            }
        }
        return null;
    }


}

