package com.synectiks.cms.graphql.types.DueDate;

import com.synectiks.commons.entities.cms.DueDate;

import java.util.List;

public class RemoveDueDatePayload  {
    private final List<DueDate> dueDates;

    public RemoveDueDatePayload(List<DueDate> dueDates) {
        this.dueDates = dueDates;
    }

    public List<DueDate> getDueDates() {
        return dueDates;
    }
}
