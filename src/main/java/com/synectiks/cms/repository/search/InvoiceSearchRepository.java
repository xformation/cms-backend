package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Invoice;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Invoice entity.
 */
public interface InvoiceSearchRepository extends JPASearchRepository<Invoice, Long> {
}
