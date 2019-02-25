package com.synectiks.cms.graphql.types.LateFee;

import com.synectiks.cms.domain.LateFee;

import java.util.List;

public class RemoveLateFeePayload {
    private final List<LateFee> lateFees;

    public RemoveLateFeePayload(List<LateFee> lateFees) {
        this.lateFees = lateFees;
    }

    public List<LateFee> getLateFees() {
        return lateFees;
    }
}
