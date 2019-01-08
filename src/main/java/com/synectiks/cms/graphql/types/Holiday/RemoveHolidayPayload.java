package com.synectiks.cms.graphql.types.Holiday;

import com.synectiks.cms.domain.Holiday;


import java.util.List;

public class RemoveHolidayPayload {

    private final List<Holiday> holidays;

    public RemoveHolidayPayload(List<Holiday> holidays){
        this.holidays = holidays;
    }

    public List<Holiday> getHolidays() {
        return holidays;
    }
}
