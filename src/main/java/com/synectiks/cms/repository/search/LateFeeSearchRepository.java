package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.LateFee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the LateFee entity.
 */
public interface LateFeeSearchRepository extends ElasticsearchRepository<LateFee, Long> {
}
