package com.synectiks.cms.graphql.types.Periods;

import com.synectiks.cms.domain.Periods;

import java.util.List;

public class RemovePeriodsPayload {
    private final List<Periods> periods;

    public RemovePeriodsPayload(List<Periods> periods) {
        this.periods = periods;
    }

    public List<Periods> getPeriods() {
        return periods;
    }
}
