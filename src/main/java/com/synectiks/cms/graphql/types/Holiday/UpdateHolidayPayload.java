package com.synectiks.cms.graphql.types.Holiday;

import com.synectiks.cms.entities.Holiday;

public class UpdateHolidayPayload extends AbstractHolidayPayload {
    public UpdateHolidayPayload(Holiday holiday){
        super(holiday);
    }
}
