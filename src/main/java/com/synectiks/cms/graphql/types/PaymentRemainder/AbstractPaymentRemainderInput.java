package com.synectiks.cms.graphql.types.PaymentRemainder;

import com.synectiks.cms.domain.enumeration.Status;

import java.util.Objects;

public class AbstractPaymentRemainderInput {
    private Long id;
    private Status feeRemainder;
    private Integer noticeDay;
    private Status overDueRemainder;
    private String remainderRecipients;

    public Status getFeeRemainder() {
        return feeRemainder;
    }

    public void setFeeRemainder(Status feeRemainder) {
        this.feeRemainder = feeRemainder;
    }

    public Integer getNoticeDay() {
        return noticeDay;
    }

    public void setNoticeDay(Integer noticeDay) {
        this.noticeDay = noticeDay;
    }

    public Status getOverDueRemainder() {
        return overDueRemainder;
    }

    public void setOverDueRemainder(Status overDueRemainder) {
        this.overDueRemainder = overDueRemainder;
    }

    public String getRemainderRecipients() {
        return remainderRecipients;
    }

    public void setRemainderRecipients(String remainderRecipients) {
        this.remainderRecipients = remainderRecipients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPaymentRemainderInput that = (AbstractPaymentRemainderInput) o;
        return Objects.equals(id, that.id) &&
            feeRemainder == that.feeRemainder &&
            Objects.equals(noticeDay, that.noticeDay) &&
            overDueRemainder == that.overDueRemainder &&
            Objects.equals(remainderRecipients, that.remainderRecipients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, feeRemainder, noticeDay, overDueRemainder, remainderRecipients);
    }

    @Override
    public String toString() {
        return "AbstractPaymentRemainderInput{" +
            "id=" + id +
            ", feeRemainder=" + feeRemainder +
            ", noticeDay=" + noticeDay +
            ", overDueRemainder=" + overDueRemainder +
            ", remainderRecipients='" + remainderRecipients + '\'' +
            '}';
    }
}
