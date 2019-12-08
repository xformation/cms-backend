package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.PaymentRequestResponse;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PaymentRequestResponse} entity.
 */
public interface PaymentRequestResponseSearchRepository extends JPASearchRepository<PaymentRequestResponse, Long> {
}
