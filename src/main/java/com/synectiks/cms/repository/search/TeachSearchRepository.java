package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Teach;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Teach entity.
 */
public interface TeachSearchRepository extends ElasticsearchRepository<Teach, Long> {
}
