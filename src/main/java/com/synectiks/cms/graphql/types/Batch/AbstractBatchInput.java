package com.synectiks.cms.graphql.types.Batch;

import com.synectiks.cms.domain.enumeration.BatchEnum;

import java.util.Objects;

public class AbstractBatchInput {
    private Long id;
    private BatchEnum batch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BatchEnum getBatch() {
        return batch;
    }

    public void setBatch(BatchEnum batch) {
        this.batch = batch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBatchInput)) return false;
        AbstractBatchInput that = (AbstractBatchInput) o;
        return Objects.equals(getId(), that.getId()) &&
            getBatch() == that.getBatch();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBatch());
    }

    @Override
    public String toString() {
        return "AbstractBatchInput{" +
            "id=" + id +
            ", batch=" + batch +
            '}';
    }
}
