package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Invoice;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Invoice entity.
 */
@Repository
public interface InvoiceRepository extends JPASearchRepository<Invoice, Long> {

}
