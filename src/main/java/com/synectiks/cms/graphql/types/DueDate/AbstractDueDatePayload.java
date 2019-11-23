package com.synectiks.cms.graphql.types.DueDate;

import com.synectiks.commons.entities.cms.DueDate;

public class AbstractDueDatePayload extends AbstractDueDateInput {
    private final DueDate dueDate;

    public AbstractDueDatePayload(DueDate dueDate) {
        this.dueDate = dueDate;
    }

    public DueDate getDueDate() {
        return dueDate;
    }
}
