package com.synectiks.cms.graphql.types.Invoice;

import com.synectiks.cms.domain.Invoice;

public class UpdateInvoicePaylaod  extends AbstractInvoicePayload{
    public UpdateInvoicePaylaod(Invoice invoice) {
        super(invoice);
    }
}
