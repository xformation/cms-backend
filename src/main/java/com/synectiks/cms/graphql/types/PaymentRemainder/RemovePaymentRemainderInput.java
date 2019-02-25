package com.synectiks.cms.graphql.types.PaymentRemainder;

public class RemovePaymentRemainderInput {
    private Long paymentRemainderId;

    public Long getPaymentRemainderId() {
        return paymentRemainderId;
    }

    public RemovePaymentRemainderInput(Long paymentRemainderId) {
        this.paymentRemainderId = paymentRemainderId;
    }
}
