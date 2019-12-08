package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Invoice;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Invoice entity.
 */
@Repository
public interface InvoiceRepository extends JPASearchRepository<Invoice, Long> {

}
