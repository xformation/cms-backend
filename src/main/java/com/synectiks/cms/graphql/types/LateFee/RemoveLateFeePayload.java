package com.synectiks.cms.graphql.types.LateFee;

import java.util.List;

import com.synectiks.cms.entities.LateFee;

public class RemoveLateFeePayload {
    private final List<LateFee> lateFees;

    public RemoveLateFeePayload(List<LateFee> lateFees) {
        this.lateFees = lateFees;
    }

    public List<LateFee> getLateFees() {
        return lateFees;
    }
}
