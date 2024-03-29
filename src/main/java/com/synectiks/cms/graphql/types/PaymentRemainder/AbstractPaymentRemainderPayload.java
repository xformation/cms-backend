package com.synectiks.cms.graphql.types.PaymentRemainder;

import com.synectiks.cms.domain.PaymentRemainder;

public class AbstractPaymentRemainderPayload {
    private final  PaymentRemainder paymentRemainder;

    public AbstractPaymentRemainderPayload(PaymentRemainder paymentRemainder) {
        this.paymentRemainder = paymentRemainder;
    }

    public PaymentRemainder getPaymentRemainder() {
        return paymentRemainder;
    }
}
