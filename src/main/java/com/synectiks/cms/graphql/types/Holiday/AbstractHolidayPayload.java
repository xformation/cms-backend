package com.synectiks.cms.graphql.types.Holiday;


import com.synectiks.cms.domain.Holiday;

public class AbstractHolidayPayload {
    private final Holiday holiday;

    public AbstractHolidayPayload(Holiday holiday){
        this.holiday = holiday;
    }

    public Holiday getHoliday() {
        return holiday;
    }
}
