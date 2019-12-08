package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Invoice;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Invoice entity.
 */
public interface InvoiceSearchRepository extends JPASearchRepository<Invoice, Long> {
}
