package com.synectiks.cms.graphql.types.Invoice;

import com.synectiks.cms.domain.Invoice;

import java.util.List;

public class RemoveInvoicePayload {
    private final List<Invoice> invoices;

    public RemoveInvoicePayload(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
}
