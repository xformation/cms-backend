package com.synectiks.cms.graphql.types.PaymentRemainder;

import com.synectiks.cms.domain.PaymentRemainder;

public class UpdatePaymentRemainderPayload extends AbstractPaymentRemainderPayload {
    public UpdatePaymentRemainderPayload(PaymentRemainder paymentRemainder) {
        super(paymentRemainder);
    }
}
