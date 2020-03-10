package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.LateFee;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the LateFee entity.
 */
public interface LateFeeSearchRepository extends JPASearchRepository<LateFee, Long> {
}
