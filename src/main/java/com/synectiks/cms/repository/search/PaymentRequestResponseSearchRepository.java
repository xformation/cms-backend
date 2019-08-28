package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.PaymentRequestResponse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PaymentRequestResponse} entity.
 */
public interface PaymentRequestResponseSearchRepository extends ElasticsearchRepository<PaymentRequestResponse, Long> {
}
