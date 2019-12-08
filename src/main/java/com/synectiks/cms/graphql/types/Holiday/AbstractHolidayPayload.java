package com.synectiks.cms.graphql.types.Holiday;


import com.synectiks.cms.entities.Holiday;

public class AbstractHolidayPayload {
    private final Holiday holiday;

    public AbstractHolidayPayload(Holiday holiday){
        this.holiday = holiday;
    }

    public Holiday getHoliday() {
        return holiday;
    }
}
