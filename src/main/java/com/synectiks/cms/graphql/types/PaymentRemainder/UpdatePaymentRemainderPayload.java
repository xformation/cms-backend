package com.synectiks.cms.graphql.types.PaymentRemainder;

import com.synectiks.commons.entities.cms.PaymentRemainder;

public class UpdatePaymentRemainderPayload extends AbstractPaymentRemainderPayload {
    public UpdatePaymentRemainderPayload(PaymentRemainder paymentRemainder) {
        super(paymentRemainder);
    }
}
