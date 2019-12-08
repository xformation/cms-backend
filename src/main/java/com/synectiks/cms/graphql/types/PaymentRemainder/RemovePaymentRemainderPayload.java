package com.synectiks.cms.graphql.types.PaymentRemainder;

import java.util.List;

import com.synectiks.cms.entities.PaymentRemainder;

public class RemovePaymentRemainderPayload {
    private final List<PaymentRemainder>paymentRemainders;

    public RemovePaymentRemainderPayload(List<PaymentRemainder> paymentRemainders) {
        this.paymentRemainders = paymentRemainders;
    }

    public List<PaymentRemainder> getPaymentRemainders() {
        return paymentRemainders;
    }
}
