package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.PaymentRemainder;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the PaymentRemainder entity.
 */
public interface PaymentRemainderSearchRepository extends JPASearchRepository<PaymentRemainder, Long> {
}
