package com.synectiks.cms.graphql.types.PaymentRemainder;

import com.synectiks.cms.domain.PaymentRemainder;

import java.util.List;

public class RemovePaymentRemainderPayload {
    private final List<PaymentRemainder>paymentRemainders;

    public RemovePaymentRemainderPayload(List<PaymentRemainder> paymentRemainders) {
        this.paymentRemainders = paymentRemainders;
    }

    public List<PaymentRemainder> getPaymentRemainders() {
        return paymentRemainders;
    }
}
