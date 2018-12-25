package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Periods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Periods entity.
 */
public interface PeriodsSearchRepository extends ElasticsearchRepository<Periods, Long> {
}
