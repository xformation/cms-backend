package com.synectiks.cms.graphql.types.Invoice;

import com.synectiks.cms.entities.Invoice;

public class UpdateInvoicePayload  extends AbstractInvoicePayload{
    public UpdateInvoicePayload(Invoice invoice) {
        super(invoice);
    }
}
