package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Documents;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Documents entity.
 */
public interface DocumentsSearchRepository extends ElasticsearchRepository<Documents, Long> {
}
