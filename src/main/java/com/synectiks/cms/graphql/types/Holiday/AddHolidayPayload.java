package com.synectiks.cms.graphql.types.Holiday;

import com.synectiks.commons.entities.cms.Holiday;

public class AddHolidayPayload extends AbstractHolidayPayload {

    public AddHolidayPayload(Holiday holiday){
        super(holiday);
    }
}
