package com.synectiks.cms.graphql.types.Invoice;

import com.synectiks.cms.domain.Invoice;

public class UpdateInvoicePayload  extends AbstractInvoicePayload{
    public UpdateInvoicePayload(Invoice invoice) {
        super(invoice);
    }
}
