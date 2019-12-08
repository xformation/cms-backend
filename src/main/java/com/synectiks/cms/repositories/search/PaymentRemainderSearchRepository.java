package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.PaymentRemainder;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the PaymentRemainder entity.
 */
public interface PaymentRemainderSearchRepository extends JPASearchRepository<PaymentRemainder, Long> {
}
