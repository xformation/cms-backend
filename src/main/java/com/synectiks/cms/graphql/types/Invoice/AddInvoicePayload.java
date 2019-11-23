package com.synectiks.cms.graphql.types.Invoice;

import com.synectiks.commons.entities.cms.Invoice;

public class AddInvoicePayload extends AbstractInvoicePayload {
    public AddInvoicePayload(Invoice invoice) {
        super(invoice);
    }
}
