package com.synectiks.cms.graphql.types.Periods;

import com.synectiks.cms.domain.Periods;

public class AbstractPeriodsPayload {
    private final Periods periods;

    public AbstractPeriodsPayload(Periods periods) {
        this.periods = periods;
    }

    public Periods getPeriods() {
        return periods;
    }
}
