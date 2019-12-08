package com.synectiks.cms.graphql.types.DueDate;

import java.util.List;

import com.synectiks.cms.entities.DueDate;

public class RemoveDueDatePayload  {
    private final List<DueDate> dueDates;

    public RemoveDueDatePayload(List<DueDate> dueDates) {
        this.dueDates = dueDates;
    }

    public List<DueDate> getDueDates() {
        return dueDates;
    }
}
