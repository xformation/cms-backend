package com.synectiks.cms.graphql.types.Holiday;

import com.synectiks.cms.domain.Holiday;

public class AddHolidayPayload extends AbstractHolidayPayload {

    public AddHolidayPayload(Holiday holiday){
        super(holiday);
    }
}
