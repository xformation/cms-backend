package com.synectiks.cms.graphql.types.Invoice;

import java.util.List;

import com.synectiks.cms.entities.Invoice;

public class RemoveInvoicePayload {
    private final List<Invoice> invoices;

    public RemoveInvoicePayload(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
}
