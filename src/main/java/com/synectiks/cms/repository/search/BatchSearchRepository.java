package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Batch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Batch entity.
 */
public interface BatchSearchRepository extends ElasticsearchRepository<Batch, Long> {
}
