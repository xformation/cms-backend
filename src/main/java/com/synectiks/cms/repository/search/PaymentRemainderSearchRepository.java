package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.PaymentRemainder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PaymentRemainder entity.
 */
public interface PaymentRemainderSearchRepository extends ElasticsearchRepository<PaymentRemainder, Long> {
}
