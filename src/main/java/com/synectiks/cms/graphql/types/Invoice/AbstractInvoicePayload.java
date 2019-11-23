package com.synectiks.cms.graphql.types.Invoice;

import com.synectiks.commons.entities.cms.Invoice;

public class AbstractInvoicePayload {
    private final Invoice invoice;

    public AbstractInvoicePayload(Invoice invoice) {
        this.invoice = invoice;
    }

    public Invoice getInvoice() {
        return invoice;
    }
}
