package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.PaymentRequestResponse;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PaymentRequestResponse} entity.
 */
public interface PaymentRequestResponseSearchRepository extends JPASearchRepository<PaymentRequestResponse, Long> {
}
