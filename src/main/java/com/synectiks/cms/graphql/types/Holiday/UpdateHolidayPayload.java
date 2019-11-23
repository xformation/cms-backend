package com.synectiks.cms.graphql.types.Holiday;

import com.synectiks.commons.entities.cms.Holiday;

public class UpdateHolidayPayload extends AbstractHolidayPayload {
    public UpdateHolidayPayload(Holiday holiday){
        super(holiday);
    }
}
