package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.College;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the College entity.
 */
public interface CollegeSearchRepository extends ElasticsearchRepository<College, Long> {
}
