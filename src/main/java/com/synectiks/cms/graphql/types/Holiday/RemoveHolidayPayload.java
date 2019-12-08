package com.synectiks.cms.graphql.types.Holiday;

import java.util.List;

import com.synectiks.cms.entities.Holiday;

public class RemoveHolidayPayload {

    private final List<Holiday> holidays;

    public RemoveHolidayPayload(List<Holiday> holidays){
        this.holidays = holidays;
    }

    public List<Holiday> getHolidays() {
        return holidays;
    }
}
