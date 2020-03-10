package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Invoice;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Invoice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceRepository extends JPASearchRepository<Invoice, Long> {

}
